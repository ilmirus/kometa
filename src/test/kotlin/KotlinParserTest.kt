import kometa.Production
import kometa.kotlin.AST
import kometa.kotlin.KotlinGen
import kometa.kotlin.Token
import kometa.kotlin_lexer.KotlinLexer
import kometa.kotlin_parser.KotlinParser
import kometa.trace
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class KotlinParserTest {
    private fun tokenize(filename: String): List<Token> {
        val lexer = KotlinLexer()
        val match = lexer.getMatch(
            File("testData/kotlin-parser/$filename.in.kt").readText().toList(),
            Production("tokens", lexer::tokens)
        )
        if (!match.success) {
            error(match.error!!)
        }
        return match.results.filterNotNull()
    }

    private fun parse(filename: String): AST.KotlinFile {
        val parser = KotlinParser()
        val tokens = tokenize(filename)
//        trace = true
//        File("out/dump.txt").delete()
        val match = parser.getMatch(tokens, Production("kotlinFile", parser::kotlinFile))

        if (!match.success) {
            error(match.error!!)
        }
        return match.result() as AST.KotlinFile
    }

    private fun generateCode(kotlinFile: AST.KotlinFile): String = KotlinGen().generateCode(kotlinFile)

    private fun checkTestData(filename: String) {
        val expected = File("testData/kotlin-parser/$filename.out.kt").readText().replace("\r\n", "\n").trim()
        val kotlinFile = parse(filename)
        val got = generateCode(kotlinFile).trim()
        assertEquals(expected, got)
    }

    @Test
    fun testHelloWorld() {
        checkTestData("hello-world")
    }

    @Test
    fun testGlExampleAfter() {
        checkTestData("gl-example-after")
    }

    @Test
    fun testGlExampleBefore() {
        checkTestData("gl-example-before")
    }

    @Test
    fun temp() {
        val ast = parse("temp")
        println(ast)
    }
}