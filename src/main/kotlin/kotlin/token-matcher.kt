package kometa.kotlin

import kometa.MatchState
import kometa.Matcher

abstract class TokenMatcher<TResult>: Matcher<Token, TResult>() {
    override fun formatErrorString(memo: MatchState<Token, TResult>, index: Int): String {
        val input = memo.input as List<Token>
        val rest = input.drop(index)
        var endIndex = rest.indexOfFirst { it is Token.NewLine }
        if (endIndex < 0) endIndex = rest.size
        val withoutRest = input.subList(0, endIndex)
        var startIndex = withoutRest.indexOfLast { it is Token.NewLine }
        if (startIndex < 0) startIndex = 0
        val line = input.subList(startIndex, endIndex)
        val str = line.joinToString(separator = " ") { it.toString() }
        val indent = StringBuffer()
        for (i in 0..(index-startIndex)) {
            indent.append(' ')
            for (c in line[i].toString()) {
                indent.append(' ')
            }
        }
        return "$str\n$indent"
    }

}