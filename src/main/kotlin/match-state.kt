package kometa

/**
 * Holds memoization and left-recursion state during a parse.
 *
 * @param TInput The input type.
 * @param TResult The result type.
 */
class MatchState<TInput, TResult>(
    /**
     * The input stream for the grammar to parse.
     */
    val input: Iterable<TInput>
) {
    /**
     * Used to pass properties specific to derived matcher classes.
     */
    val properties: Map<String, Any?> = hashMapOf()

    // rulename -> expansion -> index -> item
    private val memoTable: MutableMap<String, Expansions<TInput, TResult>> = hashMapOf()

    // rulename -> index -> lrrecord
    private val currentRecursions: MutableMap<String, MutableMap<Int, LRRecord<MatchItem<TInput, TResult>>>> =
        hashMapOf()

    /**
     * The result stack used while matching.
     */
    val results: MutableList<MatchItem<TInput, TResult>?> = arrayListOf()

    /**
     * The result stack used while matching arguments.
     */
    val argsResults: MutableList<MatchItem<TInput, TResult>?> = arrayListOf()

    /**
     * The call stack used while matching.
     */
    val callStack: MutableList<LRRecord<MatchItem<TInput, TResult>>> = arrayListOf()

    /**
     * Used by calling code to store special positions, e.g. line numbers.
     */
    val positions: MutableSet<Int> = hashSetOf()

    private var lastErrorPos = -1
    private val errorMsgs: MutableList<() -> String> = arrayListOf()
    private var lastError_: String? = null

    /**
     * The error message from the rightmost error encountered while matching.
     * Is null if there are no errors.
     *
     * @see lastErrorIndex
     */
    val lastError: String?
        get() {
            if (lastError_ == null && errorMsgs.isNotEmpty()) {
                val strings = errorMsgs.map { it() }
                val results = arrayListOf<String>()
                val expected = mutableSetOf<String>()

                for (str in strings) {
                    val match = EXPECTED.matchEntire(str)
                    if (match != null) {
                        expected += match.groups[1]!!.value
                    } else {
                        results += str
                    }
                }

                if (expected.isNotEmpty()) {
                    results += buildString {
                        append("expected ")
                        if (expected.size > 1) {
                            append(expected.take(expected.size - 1).joinToString(separator = ", "))
                            append(" or ")
                        }
                        append(expected.last())
                    }
                }

                lastError_ = results.joinToString(separator = ";") + " at $lastErrorPos"
            }
            return lastError_
        }

    /**
     * The position in the input enumerable of the rightmost error encountered while matching.
     *
     * @see lastError
     */
    val lastErrorIndex: Int
        get() = lastErrorPos

    /**
     * Allows the memo tables and left-recursion records to be garbage collect.
     */
    internal fun clearMemoTable() {
        memoTable.clear()
        currentRecursions.clear()
    }

    /**
     * Memoize the result of a production at a given index.
     *
     * @param expansion The production expansion.
     * @param index The input position.
     * @param item The result of the parse.
     */
    fun memoize(expansion: Expansion, index: Int, item: MatchItem<TInput, TResult>?) {
        val expansions = memoTable.getOrPut(expansion.name) { hashMapOf() }
        val rules = expansions.getOrPut(expansion.num) { hashMapOf() }
        rules[index] = item
    }

    /**
     * Forget a memoized result.
     *
     * @param expansion The production expansion.
     * @param index The input position.
     */
    fun forgetMemo(expansion: Expansion, index: Int) {
        memoTable[expansion.name]?.get(expansion.num)?.remove(index)
    }

    /**
     * Find the memo of a given rule call.
     *
     * @param expansion The production expansion.
     * @param index The input position
     *
     * @return found memo or null if no memo found.
     */
    fun getMemo(expansion: Expansion, index: Int): MatchItem<TInput, TResult>? =
        memoTable[expansion.name]?.get(expansion.num)?.get(index)


    /**
     * Start a left-recursion record for a rule at a given index.
     *
     * @param expansion The production expansion.
     * @param index The input position.
     * @param record The new left-recursion record.
     */

    fun startLRRecord(expansion: Expansion, index: Int, record: LRRecord<MatchItem<TInput, TResult>>){
        currentRecursions.getOrPut(expansion.name) { hashMapOf() }[index] = record
    }

    /**
     * Discard a left-recursion record for a rule at a given index.
     *
     * @param expansion The production expansion.
     * @param index The input position.
     */
    fun forgetLRRecord(expansion: Expansion, index: Int) {
        currentRecursions[expansion.name]?.remove(index)
    }

    /**
     * Get the left-recursion record for a rule at a given index.
     *
     * @param expansion The production expansion.
     * @param index The input position.
     *
     * @return The left-recursion record or null of not found.
     */
    fun getLRRecord(expansion: Expansion, index: Int): LRRecord<MatchItem<TInput, TResult>>? =
        currentRecursions[expansion.name]?.get(index)

    // TODO: Do not use functions, use raw strings
    /**
     * Sets the current error, if it is beyond or equal to the previous error.
     *
     * @param pos Position of the error.
     * @param message Function to generate the message (deferred until the end for better performance).
     */
    fun addError(pos: Int, message: () -> String) {
        if (pos > lastErrorPos) {
            errorMsgs += message
            lastErrorPos = pos
        }
    }

    /**
     * Sets the current error if it is beyond or equal to the previous error.
     *
     * @param pos Position of the error.
     * @param message Message.
     */
    fun addError(pos: Int, message: String) {
        addError(pos) { message }
    }

    /**
     * Clears all errors for a given position.
     */
    fun clearErrors() {
        errorMsgs.clear()
        lastErrorPos = -1
    }

    private var sortedPositions: IntArray = IntArray(0)

    /**
     * Gets the "line" as defined by the Positions array.  This assumes that the positions
     * mark the beginning of the "lines".
     *
     * @param index The index in question in the input.
     *
     * @return The 1-based line number in which the index is found.
     * @return The offset of the index from the beginning of the line.
     * @return The sequence of tokens in the line.
     */
    fun getLine(index: Int): Triple<Int, Int, Iterable<TInput>> {
        if (sortedPositions.isEmpty() || sortedPositions.size != positions.size) {
            positions.add(0)
            sortedPositions = positions.sorted().toIntArray()
        }

        var srch = sortedPositions.binarySearch(index)
        val srchOffset = if (sortedPositions[0] == 0) 1 else 2

        val start: Int
        val next: Int
        val lineNum: Int
        val lineOffset: Int

        if (srch >= 0) {
            // we are on the the beginning of the line
            start = sortedPositions[srch]
            next = if (srch + 1 < sortedPositions.size) sortedPositions[srch + 1] else Int.MAX_VALUE

            lineNum = srch + srchOffset
            lineOffset = 0
        } else {
            // srch is the index of the next largest position
            srch = srch.inv()
            when {
                srch == 0 -> {
                    start = 0
                    next = sortedPositions[srch]
                }
                srch < sortedPositions.size -> {
                    start = sortedPositions[srch - 1]
                    next = sortedPositions[srch]
                }
                else -> {
                    start = sortedPositions.last()
                    next = Int.MAX_VALUE
                }
            }

            lineNum = srch + (srchOffset - 1)
            lineOffset = index - start
        }

        return Triple(lineNum, lineOffset, input.drop(start).take(next - start))
    }

    companion object {
        private val EXPECTED: Regex = Regex("expected\\s+(.*)", RegexOption.IGNORE_CASE)
    }
}

typealias Rules<TInput, TResult> = MutableMap<Int, MatchItem<TInput, TResult>?>

typealias Expansions<TInput, TResult> = MutableMap<Int, Rules<TInput, TResult>>

/**
 * Records the production name and current expansion for left-recursion handling.
 */
data class Expansion(
    /**
     * The name of the production.
     */
    var name: String,
    /**
     * The current expansion number.
     */
    var num: Int
)

/**
 * A record of the current state of left-recursion handling for a rule.
 */
class LRRecord<TItem>(
    /**
     * Whether or not left-recursion has been detected for this production.
     */
    var lrDetected: Boolean,
    /**
     * How many expansions we have generated.
     */
    var numExpansions: Int,
    /**
     * The current expansion.
     */
    var currentExpansion: Expansion,
    /**
     * The farthest extent of the match.
     */
    var currentNextIndex: Int,
    /**
     * The result of the last expansion.
     */
    var currentResult: TItem? = null,
    /**
     * Rules that are involved in expanding this left-recursion.
     */
    var involvedRules: MutableSet<String>? = null
)