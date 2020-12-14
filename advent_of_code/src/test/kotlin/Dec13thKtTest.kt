import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.asserter

class Dec13thKtTest {

    @Test
    fun dec13_readBusTimes() {
        // given an input
        val input = "7,13,x,x,59,x,31,19"
        assertEquals(arrayListOf(7,13,59,31,19), dec13_part1_readBusTimes(input))
    }

    @Test
    fun dec13_getTimeNearestNextDeparture_bus59() {
        assertEquals(5, dec13_getTimeNearestNextDeparture(59, 939))
    }

    @Test
    fun dec13_getTimeNearestNextDeparture_bus7() {
        assertEquals(6, dec13_getTimeNearestNextDeparture(7, 939))
    }

    @Test
    fun dec13_getTimeNearestNextDeparture_bus13() {
        assertEquals(10, dec13_getTimeNearestNextDeparture(13, 939))
    }



}

