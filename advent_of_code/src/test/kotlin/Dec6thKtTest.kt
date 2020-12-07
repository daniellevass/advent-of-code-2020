import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.asserter

class Dec6thKtTest {

    @Test
    fun dec6th_calculateUniqueEntries() {
        assertEquals(2 ,dec6th_calculateUniqueEntries(arrayListOf('a', 'a', 'b')) )
    }

    @Test
    fun dec6th_calculateUniqueEntries_split() {
        val input = "abcdeabc"
        val chars: List<Char> = input.toList()
        assertEquals(8, chars.size)
        assertEquals(5, dec6th_calculateUniqueEntries(chars))
    }

}

