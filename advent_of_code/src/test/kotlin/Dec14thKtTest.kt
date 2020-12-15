import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.asserter

class Dec14thKtTest {

//    @Test
//    fun dec14_intTo32BitBinaryString() {
//        assertEquals("000000000000000000000000000000001011",
////        dec14_intTo32BitBinaryString(11))
//    }
//
//    @Test
//    fun dec14_applyMaskToBinaryString() {
//        val input = "000000000000000000000000000000001011"
//        val mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
//
////        assertEquals(73, dec14_applyMaskToBinaryString(input, mask))
//    }

    @Test
    fun dec14_part2_findAllAddressesForBinaryString_1() {
        val mask =  "000000000000000000000000000000X1001X"
        val input = "000000000000000000000000000000101010"

        assertEquals(
            arrayListOf(BigInteger("26"),BigInteger("27"), BigInteger("58"), BigInteger("59")),
            dec14_part2_findAllAddressesForBinaryString(input, mask))
    }

    @Test
    fun dec14_part2_findAllAddressesForBinaryString_2() {
        val mask =  "00000000000000000000000000000000X0XX"
        val input = "000000000000000000000000000000011010"

        assertEquals(
            arrayListOf(BigInteger("16"),BigInteger("17"), BigInteger("18"), BigInteger("19"),
                BigInteger("24"),BigInteger("25"), BigInteger("26"), BigInteger("27")),
            dec14_part2_findAllAddressesForBinaryString(input, mask))
    }

    @Test
    fun dec14_part2_findAllAddressesForBinaryString_4() {
        val mask =  "00000000000000000000000000000000XXXX"
        val input = "000000000000000000000000000000011010"

        assertEquals(16, dec14_part2_findAllAddressesForBinaryString(input, mask).size)
    }

    @Test
    fun dec14_part2_findAllAddressesForBinaryString_5() {
        val mask =  "0000000000000000000000000000000XXXXX"
        val input = "000000000000000000000000000000011010"

        assertEquals(32, dec14_part2_findAllAddressesForBinaryString(input, mask).size)
    }

    @Test
    fun dec14_part2_findAllAddressesForBinaryString_6() {
        val mask =  "000000000000000000000000000000XXXXXX"
        val input = "000000000000000000000000000000011010"

        assertEquals(64, dec14_part2_findAllAddressesForBinaryString(input, mask).size)
    }

    @Test
    fun dec14_part2_findAllAddressesForBinaryString_7() {
        val mask =  "00000000000000000000000000000XXXXXXX"
        val input = "000000000000000000000000000000011010"

        assertEquals(128, dec14_part2_findAllAddressesForBinaryString(input, mask).size)
    }
}

