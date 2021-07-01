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
        assertEquals(14, parser.parse("2 * 7".toList()))
    }
}
