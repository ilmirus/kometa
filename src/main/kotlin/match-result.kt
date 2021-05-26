package kometa

/**
 * Holds the results of trying to parse an input stream.
 */
class MatchResult<TInput, TResult>(
    /**
     * The matcher that generated this result.
     */
    val matcher: Matcher<TInput, TResult>,
    /**
     * The memo object that holds the match state.
     */
    val matchState: MatchState<TInput, TResult>,
    /**
     * Whether or not the match succeeded.
     */
    val success: Boolean,
    /**
     * The index in the input stream at which the match started (usually 0).
     */
    val startIndex: Int,
    /**
     * The index in the input stream after the last item matched.
     */
    val nextIndex: Int,
    /**
     * The result of the match; possibly as a list.
     * Will be null if the match did not succeed.
     */
    val results: Iterable<TResult?>,
    /**
     * The error that caused the match to fail, if it failed.
     */
    val error: String?,
    /**
     * The index in the input stream at which the error occurred.
     */
    val errorIndex: Int
) {
    fun result(): TResult = results.last()!!
}