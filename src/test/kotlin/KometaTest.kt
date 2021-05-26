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
        val present = File("src/main/kotlin/kometa-parser.generated.kt").readText()
        assertEquals(generated, present)
    }
}