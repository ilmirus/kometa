import kometa.Production
import kometa.ast.AST
import kometa.bootstrap.bootstrapAst
import kometa.generator.KotlinGen
import java.io.File

fun main() {
    File("dump.txt").delete()

    val bootstrap = false

    val pathname = if (bootstrap) "src/main/kotlin/bootstrap-parser.kt" else "kometa-parser.generated.kt"

    val ast = if (bootstrap) bootstrapAst else {
        val input = File("matchers/parser.kometa").readText()

        val parser = kometa.generated.Parser()
        val match = parser.getMatch(input.toList(), Production("KOMetaFile", parser::KOMetaFile))

        if (!match.success) {
            error(match.error!!)
        }
        match.result() as AST.GrammarFile
    }

    val pack = if (bootstrap) "bootstrap" else "generated"

    val codegen = KotlinGen(ast, "kometa.$pack")
    File(pathname).delete()
    File(pathname).appendText(codegen.generate("kometa-parser"))
}