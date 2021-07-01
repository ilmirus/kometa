package kometa.expressionTree

import kometa.Production
import kometa.expression_tree.GLET_Plugin
import kometa.kotlin.KotlinGen
import kometa.kotlin_lexer.KotlinLexer
import kometa.util.cast
import java.io.File

fun main() {
    val input = File("src/main/kotlin/expression-tree/polyhedra-vertex.kt.in").readText()

    val tokens = KotlinLexer().tokenize(input.toList())
    val ast = GLET_Plugin().parse(tokens)
    val output = KotlinGen().generateCode(ast.cast())

    File("src/main/kotlin/expression-tree/polyhedra-vertex.kt").delete()
    File("src/main/kotlin/expression-tree/polyhedra-vertex.kt").writeText(output)
}

