import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.asserter

class Dec7thKtTest {

    @Test
    fun dec7_convertLineToMap() {
        val input = "light red bags contain 1 bright white bag, 2 muted yellow bags."
        val words = input.split(" ")

        val output = dec7_convertLineToMap(words)
        assertEquals(2 , output.size)
        assertEquals(BagContainer("bright-white", 1), output[0])
    }

    @Test
    fun dec7_converLineToMap_noOther() {
        val input = "faded blue bags contain no other bags."
        val words = input.split(" ")
        val output = dec7_convertLineToMap(words)
        assertEquals(0 , output.size)
    }

}

