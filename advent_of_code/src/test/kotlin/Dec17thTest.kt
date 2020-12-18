import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Dec17thTest {

    @Test
    fun dec17_readInput() {
        val frame = dec17_readInput("src/main/resources/dec17_sample_0_0.txt")

        assertEquals(5, frame.countAlive)
        assertEquals(3, frame.map.size)
        assertEquals(3, frame.map[0]!!.size)

        assertFalse(frame.map[0]!![0]!!)
        assertTrue(frame.map[0]!![1]!!)
        assertFalse(frame.map[0]!![2]!!)

        assertFalse(frame.map[1]!![0]!!)
        assertFalse(frame.map[1]!![1]!!)
        assertTrue(frame.map[1]!![2]!!)

        assertTrue(frame.map[2]!![0]!!)
        assertTrue(frame.map[2]!![1]!!)
        assertTrue(frame.map[2]!![2]!!)

    }

    @Test
    fun dec17_countNodesAliveAtCoords() {
        // given a game
        val frame = dec17_readInput("src/main/resources/dec17_sample_0_0.txt")

        assertEquals(5, dec17_part1_countNodesAliveAtCoords(0, 1, 1, arrayListOf(frame)))
        assertEquals(2, dec17_part1_countNodesAliveAtCoords(0, 2, 2, arrayListOf(frame)))
        assertEquals(1, dec17_part1_countNodesAliveAtCoords(0, 0, 0, arrayListOf(frame)))
    }

    @Test
    fun dec17_calculateNextCycle_1() {
        val frame = dec17_readInput("src/main/resources/dec17/dec17_sample_0_0.txt")

        // first cycle
        val cycle1 = dec17_part1_calculateNextCycle(arrayListOf(frame))
        assertEquals(3, cycle1.size)

        // count alive in each frame
        assertEquals(3, dec17_part1_getFrameForIndex(-1, cycle1)!!.countAlive)
        assertEquals(5, dec17_part1_getFrameForIndex(0, cycle1)!!.countAlive)
        assertEquals(3, dec17_part1_getFrameForIndex(1, cycle1)!!.countAlive)

        // second cycle
        val cycle2 = dec17_part1_calculateNextCycle(cycle1)
        assertEquals(5, cycle2.size)
        assertEquals(1, dec17_part1_getFrameForIndex(-2, cycle2)!!.countAlive)
        assertEquals(5, dec17_part1_getFrameForIndex(-1, cycle2)!!.countAlive)
        assertEquals(9, dec17_part1_getFrameForIndex(0, cycle2)!!.countAlive)
        assertEquals(5, dec17_part1_getFrameForIndex(1, cycle2)!!.countAlive)
        assertEquals(1, dec17_part1_getFrameForIndex(2, cycle2)!!.countAlive)

    }

}

