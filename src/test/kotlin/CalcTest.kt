import kometa.Production
import kometa.calc.Calc
import kometa.trace
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File

class CalcTest {
    @Test
    fun multiplicative() {
        val parser = Calc()
//        File("out/dump.txt").delete()
//        trace = true
        val match = parser.getMatch("2 * 7".toList(), Production("Expression", parser::Expression))
        assertTrue(match.success)
        assertEquals(14, match.result())
    }
}
