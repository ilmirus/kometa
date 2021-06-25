import kometa.Production
import kometa.ast.AST
import kometa.bootstrap.bootstrapAst
import kometa.generator.KotlinGen
import java.io.File

fun generateBootstrapParser(overwrite: Boolean) {
    val pathname =
        if (overwrite) "src/main/kotlin/bootstrap-parser.kt"
        else "out/bootstrap-parser.kt"

    val codegen = KotlinGen(bootstrapAst, "kometa.bootstrap", false)
    File(pathname).delete()
    File(pathname).appendText(codegen.generate("kometa-parser"))
}

fun generateParser(input: String, overwrite: Boolean) {
    val slashIndex = input.lastIndexOf("/")
    val name = input.substring(slashIndex + 1).removeSuffix(".kometa")
    val output =
        if (overwrite) "src/main/kotlin/generated/$name.generated.kt"
        else "out/$name.generated.kt"

    val parser = kometa.kometa_parser.Parser()
    val match = parser.getMatch(
        File(input).readText().toList(),
        Production("KOMetaFile", parser::KOMetaFile)
    )

    if (!match.success) {
        error(match.error!!)
    }

    val ast = match.result() as AST.GrammarFile

    val codegen = KotlinGen(ast, "kometa.${name.replace('-', '_')}", false)
    File(output).delete()
    File(output).appendText(codegen.generate(name))
}
