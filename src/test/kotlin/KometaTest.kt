import kometa.Production
import kometa.ast.AST
import kometa.kometa_parser.Parser
import kometa.util.cast
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

class KometaTest {
    @Test
    fun bootstrapAst() {
        generateBootstrapParser(false)
        val generated = File("out/bootstrap-parser.kt").readText()
        val present = File("src/main/kotlin/bootstrap-parser.kt").readText()
        assertEquals(generated, present)
    }

    @Test
    fun bootstrapGenerated() {
        generateParser("matchers/kometa-parser.kometa", false)
        val generated = File("out/kometa-parser.generated.kt").readText()
        val present = File("src/main/kotlin/generated/kometa-parser.generated.kt").readText()
        assertEquals(generated, present)
    }

    @Test
    fun calcGenerated() {
        generateParser("matchers/calc.kometa", false)
        val generated = File("out/calc.generated.kt").readText()
        val present = File("src/main/kotlin/generated/calc.generated.kt").readText()
        assertEquals(generated, present)
    }

    private fun parse(filename: String): AST.GrammarFile {
        val input = File("testData/kometa/$filename.kometa").readText()
        return Parser().parse(input.toList()).cast()
    }

    @Test
    fun testRuleStartingWithKeyword() {
        parse("ruleStartingWithKeyword")
    }
}