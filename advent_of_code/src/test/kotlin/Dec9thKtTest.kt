import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.asserter

class Dec9thKtTest {

    @Test
    fun dec9_isLastNumberAnAdditionOfPreviousNumbers() {
        assertTrue(dec9_isLastNumberAnAdditionOfPreviousNumbers(arrayListOf(35,20,15,25,47,40)))
    }

    @Test
    fun dec9_isLastNumberAnAdditionOfPreviousNumbers_false() {
        assertFalse(dec9_isLastNumberAnAdditionOfPreviousNumbers(arrayListOf(95,102,117,150,182,127)))
    }
}

