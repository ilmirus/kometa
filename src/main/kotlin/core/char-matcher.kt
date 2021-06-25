package kometa

open class CharMatcher<TResult>(handleLeftRecursion: Boolean) : Matcher<Char, TResult>(handleLeftRecursion) {
    override fun formatErrorString(memo: MatchState<Char, TResult>, index: Int): String {
        val input = memo.input.joinToString("")
        var endIndex = input.indexOf("\n", index)
        endIndex = if (endIndex < index) input.length else endIndex
        val withoutSuffix = input.substring(0, endIndex)
        var startIndex = withoutSuffix.lastIndexOf("\n")
        startIndex = if (startIndex < 0) 0 else (startIndex + 1)
        val line = withoutSuffix.substring(startIndex)
        val spaces = (1..(index - startIndex)).joinToString("") { " " }
        return "$line\n$spaces^\n"
    }
}