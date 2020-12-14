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

    @Test
    fun dec13_part2_doHardMaths_part1() {
        // 1789,37,47,1889

        val busses = arrayListOf(
            Bus(1789, 0),
            Bus(37, 1),
            Bus(47, 2),
            Bus(1889, 3)
        )

        assertEquals(1202161486L, dec13_part2_doHardMaths(busses))
    }

    @Test
    fun dec13_part2_doHardMaths_part1_2() {
        // 67,x,7,59,61

        val busses = arrayListOf(
            Bus(67, 0),
            Bus(7, 2),
            Bus(59, 3),
            Bus(61, 4)
        )

        assertEquals(779210L, dec13_part2_doHardMaths(busses))
    }

}

