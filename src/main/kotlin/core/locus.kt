package core

data class Locus(
    val startOffset: Int,
    val endOffset: Int
)

interface WithLocus {
    val locus: Locus
}

fun locusOf(vararg elements: WithLocus?): Locus {
    for (element in elements) {
        if (element != null) {
            return element.locus
        }
    }
    error("No element found")
}