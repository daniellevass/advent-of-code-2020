import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.asserter

class Dec5thKtTest {

    @Test
    fun dec5th_getSeatId_test1() {
        assertEquals(567 ,dec5th_getSeatId(70, 7) )
    }

    @Test
    fun dec5th_getSeatId_test2() {
        assertEquals(119 ,dec5th_getSeatId(14, 7) )
    }

    @Test
    fun dec5th_getSeatId_test3() {
        assertEquals(820 ,dec5th_getSeatId(102, 4) )
    }

    @Test
    fun dec5th_getRow_test1() {
        assertEquals(70, dec5th_getRow("BFFFBBF"))
    }
    @Test
    fun dec5th_getRow_test2() {
        assertEquals(14, dec5th_getRow("FFFBBBF"))
    }
    @Test
    fun dec5th_getRow_test3() {
        assertEquals(102, dec5th_getRow("BBFFBBF"))
    }

    @Test
    fun dec5th_getCol_test1() {
        assertEquals(7, dec5th_getColumn("RRR"))
    }
    @Test
    fun dec5th_getCol_test2() {
        assertEquals(7, dec5th_getColumn("RRR"))
    }
    @Test
    fun dec5th_getCol_test3() {
        assertEquals(4, dec5th_getColumn("RLL"))
    }

    @Test
    fun dec5th_boardingPassToSeatId_test1() {
        assertEquals(567, dec5th_boardingPassToSeatId("BFFFBBFRRR"))
    }

    @Test
    fun dec5th_boardingPassToSeatId_test2() {
        assertEquals(119, dec5th_boardingPassToSeatId("FFFBBBFRRR"))
    }

    @Test
    fun dec5th_boardingPassToSeatId_test3() {
        assertEquals(820, dec5th_boardingPassToSeatId("BBFFBBFRLL"))
    }


}

