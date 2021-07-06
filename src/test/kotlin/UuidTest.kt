import kometa.UUID.UuidParser
import org.junit.jupiter.api.Test

class UuidTest {
    @Test
    fun testInvalid() {
        try {
            UuidParser().parse("1-1-1-1".toList())
            throw AssertionError()
        } catch (e: IllegalStateException) {
            assert("expected hexDigit" in e.message!!)
        }
    }

    @Test
    fun testValid() {
        UuidParser().parse("dbce2378-de61-11eb-ba80-0242ac130004".toList())
    }
}