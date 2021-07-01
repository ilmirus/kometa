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
    // rulename -> expansion
    private val memoTable: MutableMap<String, Expansions<TInput, TResult>> = hashMapOf()

    // rulename -> index -> expansion
    private val currentRecursions: MutableMap<String, MutableMap<Int, Expansion>> =
        hashMapOf()

    /**
     * The result stack used while matching.
     */
    val results: MutableList<MatchItem<TInput, TResult>?> = arrayListOf()

    /**
     * The result stack used while matching arguments.
     */
    val argResults: MutableList<MatchItem<TInput, TResult>?> = arrayListOf()

    /**
     * The call stack used while matching.
     */
    val callStack: MutableList<Expansion> = arrayListOf()

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
     * Find the memo of a given rule call.
     *
     * @param expansion The production expansion.
     * @param index The input position
     *
     * @return found memo or null if no memo found.
     */
    fun getMemo(expansion: Expansion, index: Int): Pair<MatchItem<TInput, TResult>?, Boolean> {
        val map = memoTable[expansion.name]?.get(expansion.num) ?: return null to false
        return map[index] to map.containsKey(index)
    }


    /**
     * Start a left-recursion record for a rule at a given index.
     *
     * @param expansion The production expansion.
     * @param index The input position.
     * @param record The new left-recursion record.
     */

    fun startExpansion(expansion: Expansion, index: Int, record: Expansion){
        currentRecursions.getOrPut(expansion.name) { hashMapOf() }[index] = record
    }

    /**
     * Discard a left-recursion record for a rule at a given index.
     *
     * @param expansion The production expansion.
     * @param index The input position.
     */
    fun forgetExpansion(expansion: Expansion, index: Int) {
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
    fun getExpansion(expansion: Expansion, index: Int): Expansion? =
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
