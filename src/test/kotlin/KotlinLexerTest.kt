import kometa.Production
import kometa.kotlin.Token
import kometa.kotlin_lexer.KotlinLexer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class KotlinLexerTest {
    private fun tokenize(filename: String): List<Token> {
        val lexer = KotlinLexer()
        val match = lexer.getMatch(
            File("testData/kotlin-lexer/$filename.in.kt").readText().toList(),
            Production("tokens", lexer::tokens)
        )
        if (!match.success) {
            error(match.error!!)
        }
        return match.results.filterNotNull()
    }

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
}