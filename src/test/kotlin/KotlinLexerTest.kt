import kometa.Production
import kometa.kotlin.Token
import kometa.kotlin_lexer.KotlinLexer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File
import java.lang.IllegalStateException

class KotlinLexerTest {
    private fun tokenize(filename: String): List<Token> =
        KotlinLexer().tokenize(File("testData/kotlin-lexer/$filename.in.kt").readText().toList())

    private fun checkTestData(filename: String) {
        val expected = File("testData/kotlin-lexer/$filename.out.kt").readText().replace("\r\n", "\n")
        val tokens = tokenize(filename)
        assertEquals(expected, tokens.joinToString(" "))
    }

    @Test
    fun empty() {
        checkTestData("empty")
    }

    @Test
    fun helloWorld() {
        checkTestData("hello-world")
    }

    @Test
    fun unclosedStringLiteral1() {
        try {
            tokenize("unclosed-string-literal-1")
            throw AssertionError()
        } catch (e: IllegalStateException) {
            assertTrue("unclosed string literal" in e.message!!)
        }
    }
}