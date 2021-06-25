package kometa

import kometa.kotlin.Token
import kometa.util.*
import java.io.File
import kotlin.Exception
import kotlin.experimental.ExperimentalTypeInference
import kotlin.jvm.internal.Ref

var trace = false

typealias MatcherRule<TInput, TResult> = (MatchState<TInput, TResult>, Iterable<TInput>, Production<TInput, TResult>) -> Unit

/**
 * Base class for IronMeta grammars.
 *
 * @param TInput The type of inputs to the grammar.
 * @param TResult The type of results of grammar rules.
 */
abstract class Matcher<TInput, TResult>(
    /**
     * Whether or not the matcher should detect and process left-recursive rules correctly.
     * Setting this to false will make matching run faster, but go into an infinite loop if there is a left-recursive rule in your grammar.
     */
    val handleLeftRecursion: Boolean = true
) {
    /**
     * A utility delegate for filtering a sequence to return only non-null items.
     */
    protected val _NON_NULL: (TResult) -> Boolean = { it != null }

    /**
     * Called before a match begins.
     */
    protected var beforeMatch: MatcherRule<TInput, TResult> = { _, _, _ -> }

    /**
     * Called after a match ends.
     */
    protected var afterMatch: (MatchResult<TInput, TResult>) -> Unit = {}

    protected var terminals: Set<String> = emptySet()

    /**
     * Try to match the input.
     *
     * @param input The input to be matched.
     * @param production The top-level grammar production (rule) of the generated parser class to use.
     * @param index Index at which to start the match.
     *
     * @return The result of the match.
     */
    open fun getMatch(
        input: Iterable<TInput>,
        production: Production<TInput, TResult>,
        index: Int = 0
    ): MatchResult<TInput, TResult> = getMatch(MatchState(input), production, index)

    open fun getMatch(
        state: MatchState<TInput, TResult>,
        production: Production<TInput, TResult>,
        index: Int
    ): MatchResult<TInput, TResult> {
        beforeMatch(state, state.input, production)

        var result: MatchItem<TInput, TResult>? = null

        try {
            result = _MemoCall(state, production.methodName, index, production.op, null)
        } catch (e: Exception) {
            throw e
        }

        state.clearMemoTable()

        val matchResult =
            if (result != null) {
                MatchResult(
                    this, state, true, result.startIndex, result.nextIndex, result.results,
                    state.lastError, state.lastErrorIndex
                )
            } else MatchResult(this, state, false, -1, -1, emptyList(), state.lastError, state.lastErrorIndex)

        afterMatch(matchResult)
        return matchResult
    }

    /**
     * Calls an action that returns a list of results.
     */
    @OptIn(ExperimentalTypeInference::class)
    @OverloadResolutionByLambdaReturnType
    protected fun <ItemType> _Thunk(func: (ItemType) -> Iterable<TResult>, item: ItemType): Iterable<TResult> =
        func(item)

    /**
     * Calls an action that returns a single result.
     */
    @JvmName("_Thunk1")
    protected fun <ItemType> _Thunk(func: (ItemType) -> TResult, item: ItemType): Iterable<TResult> = listOf(func(item))

    /**
     * Call a grammar production, using memoization and handling left-recursion.
     *
     * @param memo The memo for the current match.
     * @param ruleName The name of the production.
     * @param index The current index in the input stream.
     * @param production The production itself.
     * @param args Arguments to the production (can be null).
     *
     * @return The result of the production at the given input index.
     */
    protected fun _MemoCall(
        memo: MatchState<TInput, TResult>,
        ruleName: String,
        index: Int,
        production: (MatchState<TInput, TResult>, Int, Iterable<MatchItem<TInput, TResult>>?) -> Unit,
        args: Iterable<MatchItem<TInput, TResult>>?
    ): MatchItem<TInput, TResult>? {
        if (trace) {
            val input = memo.input as List<Token>
            val rest = input.drop(index)
            var end = rest.indexOf(Token.NL)
            if (end < 0) end = rest.size
            val toPrint = rest.subList(0, end).joinToString(separator = " ") { it.toString() }
            val indent = (97 until Exception().stackTrace.size).joinToString("") { " " }
            File("out/dump.txt").appendText("$indent$ruleName, $index: $toPrint\n")
        }

        var result: MatchItem<TInput, TResult>? = null

        val expansion = Expansion(
            name = if (args == null) ruleName else ruleName + args.joinToString(separator = ", "),
            num = 0
        )

        // if we have a memo record, use that
        memo.getMemo(expansion, index)?.let {
            memo.results.push(it)
            return it
        }

        // if we are not handling left recursion, just call the production directly.
        if (!handleLeftRecursion || ruleName in terminals) {
            production(memo, index, args)

            result = memo.results.peek()

            memo.memoize(expansion, index, result)

            if (result == null) {
                memo.addError(index, "expected $ruleName")
            }
            return result
        }

        // check for left-recursion
        var record = memo.getLRRecord(expansion, index)
        if (record != null) {
            record.lrDetected = true

            val involved = memo.callStack
                .takeWhile { it.currentExpansion.name != expansion.name }
                .map { it.currentExpansion.name }

            record.involvedRules = record.involvedRules?.union(involved)?.toMutableSet() ?: hashSetOf()

            result = memo.getMemo(record.currentExpansion, index)
                ?: throw MatcherException(index, "Problem with expansion " + record.currentExpansion)

            memo.results.push(result)
        } else {
            // no lr information
            record = LRRecord(
                lrDetected = false,
                numExpansions = 1,
                currentExpansion = Expansion(expansion.name, 1),
                currentNextIndex = -1
            )

            memo.memoize(record.currentExpansion, index, null)
            memo.startLRRecord(expansion, index, record)
            memo.callStack.push(record)

            while (true) {
                production(memo, index, args)
                result = memo.results.pop()

                // do we need to keep trying the expansions?
                if (record.lrDetected && result != null && result.nextIndex > record.currentNextIndex) {
                    record.numExpansions++
                    record.currentExpansion = Expansion(expansion.name, record.numExpansions)
                    record.currentNextIndex = result.nextIndex
                    memo.memoize(record.currentExpansion, index, result)

                    record.currentResult = result
                } else {
                    // we are done trying to expand
                    if (record.lrDetected) {
                        result = record.currentResult
                    }

                    memo.forgetLRRecord(expansion, index)
                    memo.results.push(result)

                    // if we are not involved in any left-recursion expansions above us, memoize
                    memo.callStack.pop()

                    val foundLR = memo.callStack.any { it.involvedRules?.contains(expansion.name) == true }
                    if (!foundLR) {
                        memo.memoize(expansion, index, result)
                    }

                    if (result == null) {
                        memo.addError(index, "expected " + expansion.name)
                    }
                    break
                }
            }
        }
        return result
    }

    /**
     * Matches a literal string (only used with matchers on a <c>char</c> enumerable).
     *
     * @param memo Memo.
     * @param index Index.
     * @param str String to match.
     */
    protected fun _ParseLiteralString(
        memo: MatchState<TInput, TResult>,
        index: Ref.IntRef,
        str: String
    ): MatchItem<TInput, TResult>? {
        var curIndex = index.element
        var failed = false

        for (ch in str) {
            val curCh = memo.input.elementAtOrElse(curIndex) { NOT_FOUND }
            if (curCh === NOT_FOUND) {
                failed = true
                break
            }
            ++curIndex
            if (curCh != ch) {
                failed = true
                break
            }
        }

        if (!failed) {
            val result = MatchItem<TInput, TResult>(index.element, curIndex, memo.input)
            index.element = curIndex
            memo.results.push(result)
            return result
        }
        memo.results.push(null)
        return null
    }

    /**
     * Matches a literal char (only used with matchers on a <c>char</c> enumerable).
     *
     * @param memo Memo.
     * @param index Index.
     * @param ch Character to match.
     */
    protected fun _ParseLiteralChar(
        memo: MatchState<TInput, TResult>,
        index: Ref.IntRef,
        ch: Char
    ): MatchItem<TInput, TResult>? {
        val curCh = memo.input.elementAtOrElse(index.element) { NOT_FOUND }
        if (curCh != ch) {
            memo.results.push(null)
            return null
        }

        val result = MatchItem<TInput, TResult>(index.element, index.element + 1, memo.input)
        index.element++
        memo.results.push(result)
        return result
    }

    /**
     * Matches a literal object.
     *
     * @param memo Memo.
     * @param index Index.
     * @param obj Object to match.
     */
    protected fun _ParseLiteralObj(
        memo: MatchState<TInput, TResult>,
        index: Ref.IntRef,
        obj: Any?
    ): MatchItem<TInput, TResult>? {
        if (obj is Iterable<*>) {
            var curIndex = index.element
            var failed = false

            for (input in obj) {
                val curInput = memo.input.elementAtOrElse(curIndex) { NOT_FOUND }
                curIndex++
                if (input != curInput) {
                    failed = true
                    break
                }
            }

            if (!failed) {
                val result = MatchItem<TInput, TResult>(index.element, curIndex, memo.input)
                memo.results.push(result)
                index.element = curIndex
                return result
            }
        } else {
            val curInput = memo.input.elementAtOrElse(index.element) { NOT_FOUND }
            if (obj == curInput) {
                val result = MatchItem<TInput, TResult>(index.element, index.element + 1, memo.input)
                memo.results.push(result)
                index.element++
                return result
            }
        }
        memo.results.push(null)
        return null
    }

    /**
     * Matches a literal object in an argument stream.
     * @param memo Memo.
     * @param itemIndex Item index.
     * @param inputIndex Input index.
     * @param obj Object to match.
     * @param args Argument stream.
     */
    protected fun _ParseLiteralArgs(
        memo: MatchState<TInput, TResult>,
        itemIndex: Ref.IntRef,
        inputIndex: Ref.IntRef,
        obj: Any?,
        args: Iterable<MatchItem<TInput, TResult>>?
    ): MatchItem<TInput, TResult>? {
        if (args != null) {
            if (obj is Iterable<*>) {
                val oldItemIndex = itemIndex.element
                var curItemIndex = itemIndex.element
                var curInputIndex = inputIndex.element

                val inputList = arrayListOf<TInput>()
                for (input in obj) {
                    val curItem = args.elementAtOrElse(curItemIndex) { NOT_FOUND }
                    if (curItem is MatchItem<*, *>) {
                        val curInput = curItem.inputs.elementAtOrElse(curInputIndex) { NOT_FOUND }
                        if (input == curInput) {
                            inputList.add(curInput.cast())
                            if (curInputIndex + 1 >= curItem.inputs.toList().size) {
                                curItemIndex++
                                curInputIndex = 0
                            } else {
                                inputIndex.element++
                            }
                        }
                    }
                }

                itemIndex.element = curItemIndex
                inputIndex.element = curItemIndex

                val result = MatchItem<TInput, TResult>(oldItemIndex, itemIndex.element, inputList)
                memo.argResults.push(result)
                return result
            } else {
                val oldItemIndex = itemIndex.element
                val curItem = args.elementAtOrElse(itemIndex.element) { NOT_FOUND }
                if (curItem is MatchItem<*, *>) {
                    val curInput = curItem.inputs.elementAtOrElse(inputIndex.element) { NOT_FOUND }
                    if (obj == curInput) {
                        // increment
                        if (inputIndex.element + 1 >= curItem.inputs.toList().size) {
                            itemIndex.element++
                            inputIndex.element = 0
                        } else {
                            inputIndex.element = 0
                        }
                        val result =
                            MatchItem<TInput, TResult>(oldItemIndex, itemIndex.element, listOf(curInput.cast()))
                        memo.argResults.push(result)
                        return result
                    }
                }
            }
        }

        memo.argResults.push(null)
        return null
    }

    protected fun _ParseRegexp(
        memo: MatchState<TInput, TResult>,
        index: Ref.IntRef,
        re: Regex
    ): MatchItem<TInput, TResult>? {
        if (memo.input.firstOrNull() !is Char) {
            memo.results.push(null)
            return null
        }

        var curIndex = index.element
        var succIndex = -1

        do {
            val str = memo.input.take(curIndex).joinToString(separator = "")
            if (re.matches(str)) {
                succIndex = curIndex
            } else {
                break
            }
        } while (++curIndex < memo.input.toList().size)

        if (succIndex >= 0) {
            val result = MatchItem<TInput, TResult>(index.element, succIndex + 1, memo.input)
            index.element = succIndex + 1
            memo.results.push(result)
            return result
        }
        memo.results.push(null)
        return null
    }

    /**
     * Matches a set of characters.  Only used for matchers over <c>char</c> enumerables.
     *
     * @param memo Memo.
     * @param index Index.
     * @param chars Characters to match.
     */
    protected fun _ParseInputClass(
        memo: MatchState<TInput, TResult>,
        index: Ref.IntRef,
        chars: List<Char>
    ): MatchItem<TInput, TResult>? {
        val input = memo.input.elementAtOrElse(index.element) { NOT_FOUND }
        if (input in chars) {
            val result = MatchItem<TInput, TResult>(index.element, index.element + 1, memo.input)
            index.element++
            memo.results.push(result)
            return result
        }
        memo.results.push(null)
        return null
    }

    /**
     * Matches a set of characters in an argument stream.
     *
     * @param memo Memo.
     * @param itemIndex Item index.
     * @param inputIndex Input index.
     * @param args Argument stream.
     * @param chars Characters to match.
     */
    protected fun _ParseInputClassArgs(
        memo: MatchState<TInput, TResult>,
        itemIndex: Ref.IntRef,
        inputIndex: Ref.IntRef,
        args: Iterable<MatchItem<TInput, TResult>>,
        chars: List<Char>
    ): MatchItem<TInput, TResult>? {
        var curItemIndex = itemIndex.element
        var curInputIndex = inputIndex.element

        val curItem = args.elementAtOrElse(curItemIndex) { NOT_FOUND }
        if (curItem is MatchItem<*, *>) {
            val curInput = curItem.inputs.elementAtOrElse(curInputIndex) { NOT_FOUND }
            if (curInput in chars) {
                if (curInputIndex + 1 >= curItem.inputs.toList().size) {
                    curItemIndex++
                    curInputIndex = 0
                } else {
                    curInputIndex++
                }

                var result = MatchItem<TInput, TResult>(itemIndex.element, curItemIndex, listOf(curInput.cast()))

                itemIndex.element = curItemIndex
                inputIndex.element = curInputIndex

                memo.argResults.push(result)
                return result
            }
        }

        memo.argResults.push(null)
        return null
    }

    /**
     * Matches any input.
     *
     * @param memo Memo.
     * @param index Index.
     */
    protected fun _ParseAny(memo: MatchState<TInput, TResult>, index: Ref.IntRef): MatchItem<TInput, TResult>? {
        val temp = memo.input.elementAtOrElse(index.element) { NOT_FOUND }
        if (temp !== NOT_FOUND) {
            val result = MatchItem<TInput, TResult>(index.element, index.element + 1, memo.input)
            index.element++
            memo.results.push(result)
            return result
        }
        memo.results.push(null)
        memo.addError(index.element, "unexpected end of file")
        return null
    }

    /**
     * Matches any input in an argument stream.
     *
     * @param memo Memo.
     * @param itemIndex Item index.
     * @param inputIndex Input index.
     * @param args Argument stream.
     */
    protected fun _ParseAnyArgs(
        memo: MatchState<TInput, TResult>,
        itemIndex: Ref.IntRef,
        inputIndex: Ref.IntRef,
        args: Iterable<MatchItem<TInput, TResult>>?
    ): MatchItem<TInput, TResult>? {
        if (args != null) {
            if (inputIndex.element == 0) {
                val tmp = args.elementAtOrElse(itemIndex.element) { NOT_FOUND }
                if (tmp !== NOT_FOUND) {
                    itemIndex.element++
                    memo.argResults.push(tmp.cast())
                    return tmp.cast()
                }
            }
        }
        memo.argResults.push(null)
        memo.addError(inputIndex.element, "not enough arguments")
        return null
    }

    protected fun _ParseAnyArgs(
        memo: MatchState<TInput, TResult>,
        argIndex: Ref.IntRef,
        args: Iterable<MatchItem<TInput, TResult>>?
    ): MatchItem<TInput, TResult>? {
        if (args != null) {
            val item = args.elementAtOrElse(argIndex.element) { NOT_FOUND }
            if (item !== NOT_FOUND) {
                memo.argResults.push(item.cast())
                argIndex.element++
                return item.cast()
            }
        }

        memo.argResults.push(null)
        memo.addError(argIndex.element, "not enough arguments")
        return null
    }

    protected abstract fun formatErrorString(memo: MatchState<TInput, TResult>, index: Int): String

    protected fun _ABSURD(_memo: MatchState<TInput, TResult>, __index: Int, _args: Iterable<MatchItem<TInput, TResult>>?) {
        _memo.results.push(null)
    }

    protected val MatchItem<TInput, TResult>?.r: TResult
        get() = nr!!

    protected val MatchItem<TInput, TResult>?.nr: TResult?
        get() = this?.asResult()

    protected val MatchItem<TInput, TResult>?.i: TInput
        get() = this?.asInput()!!

    protected val MatchItem<TInput, TResult>?.ni: TInput?
        get() = this?.asInput()

    protected val MatchItem<TInput, TResult>?.l: List<TResult>
        get() = this?.results?.filterNotNull() ?: emptyList()

    protected val MatchItem<TInput, TResult>?.il: List<TInput>
        get() = this?.inputs?.filterNotNull() ?: emptyList()

    protected val MatchItem<TInput, TResult>?.s: String
        get() = this.il.joinToString("")

    protected val MatchItem<TInput, TResult>?.ns: String?
        get() = this?.inputs?.filterNotNull()?.joinToString("")

    companion object {
        private val NOT_FOUND = Any()
    }
}

data class Production<TInput, TResult>(
    val methodName: String,
    val op: (MatchState<TInput, TResult>, Int, Iterable<MatchItem<TInput, TResult>>?) -> Unit
)

class MatcherException(val index: Int, message: String) : RuntimeException(message)