package core

import kometa.MatchItem

data class Locus(
    val startOffset: Int,
    val endOffset: Int
)

interface WithLocus {
    val locus: Locus
}

const val UNDEFINED_OFFSET: Int = -1