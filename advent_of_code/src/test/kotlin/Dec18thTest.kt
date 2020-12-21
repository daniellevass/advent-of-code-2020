import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Dec18thTest {

    @Test
    fun dec18_case1() {
        val input = "1 + 2 * 3 + 4 * 5 + 6"
        assertEquals(71, dec18_readLine(input))
    }

    @Test
    fun dec18_case2() {
        val input = "1 + (2 * 3) + (4 * (5 + 6))"
        assertEquals(51, dec18_readLine(input))
    }

    @Test
    fun dec18_case3() {
        val input = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"
        assertEquals(13632, dec18_readLine(input))
    }

    @Test
    fun dec18_part2_case1() {
        val input = "1 + 2 * 3 + 4 * 5 + 6"
        assertEquals(231, dec18_part2(input))
    }

    @Test
    fun dec18_part2_case2() {
        val input = "1 + (2 * 3) + (4 * (5 + 6))"
        assertEquals(51, dec18_part2(input))
    }

    @Test
    fun dec18_part2_case3() {
        val input = "2 * 3 + (4 * 5)"
        // 2*(3+()4*5)
        assertEquals(46, dec18_part2(input))
    }

    @Test
    fun dec18_part2_case4() {
        val input = "5 + (8 * 3 + 9 + 3 * 4 * 3)"
        assertEquals(1445, dec18_part2(input))
    }



    @Test
    fun dec18_findMatchingEndBracketIndex_1() {
        val input = "1 + (2 * 3) + (4 * (5 + 6))"
        assertEquals(10, dec18_findMatchingEndBracketIndex(input, 4))
    }

    @Test
    fun dec18_findMatchingEndBracketIndex_2() {
        val input = "1 + (2 * 3) + (4 * (5 + 6))"
        assertEquals(26, dec18_findMatchingEndBracketIndex(input, 14))
    }

    @Test
    fun dec18_findMatchingEndBracketIndex_3() {
        val input = "1 + (2 * 3) + (4 * (5 + 6))"
        assertEquals(25, dec18_findMatchingEndBracketIndex(input, 19))
        assertEquals("5 + 6", input.substring(20, 25))
    }

}

