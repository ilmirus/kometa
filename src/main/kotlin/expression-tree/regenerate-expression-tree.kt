package kometa.expressionTree

import kometa.Production
import kometa.expression_tree.GLET_Plugin
import kometa.kotlin.KotlinGen
import kometa.kotlin_lexer.KotlinLexer
import kometa.util.cast
import java.io.File

fun main() {
    val input = File("src/main/kotlin/expression-tree/polyhedra-vertex.kt.in").readText()
    val lexer = KotlinLexer()
    val lexerMatch = lexer.getMatch(input.toList(), Production("tokens", lexer::tokens))
    if (!lexerMatch.success) error(lexerMatch.error!!)

    val tokens = lexerMatch.results.filterNotNull()
    val parser = GLET_Plugin()
    val parserMatch = parser.getMatch(tokens, Production("kotlinFile", parser::kotlinFile))
    if (!parserMatch.success) error(parserMatch.error!!)

    val output = KotlinGen().generateCode(parserMatch.result().cast())
    File("src/main/kotlin/expression-tree/polyhedra-vertex.kt").delete()
    File("src/main/kotlin/expression-tree/polyhedra-vertex.kt").writeText(output)
}

