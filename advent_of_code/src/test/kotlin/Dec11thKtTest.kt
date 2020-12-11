import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.asserter

class Dec11thKtTest {

    @Test
    fun dec11_calculateTotalSeatsTaken_frame0() {
        val game = dec11_fileToGame("src/main/resources/dec11_sample0.txt")
        assertEquals(0, dec11_calculateTotalSeatsTaken(game))
    }

    @Test
    fun dec11_calculateTotalSeatsTaken_frame5() {
        val game = dec11_fileToGame("src/main/resources/dec11_sample5.txt")
        assertEquals(37, dec11_calculateTotalSeatsTaken(game))
    }

    @Test
    fun dec11_dec11_calculateNextStep_frames() {
        val frame0 = dec11_fileToGame("src/main/resources/dec11_sample0.txt")
        val frame1 = dec11_fileToGame("src/main/resources/dec11_sample1.txt")
        val frame2 = dec11_fileToGame("src/main/resources/dec11_sample2.txt")
        val frame3 = dec11_fileToGame("src/main/resources/dec11_sample3.txt")
        val frame4 = dec11_fileToGame("src/main/resources/dec11_sample4.txt")
        val frame5 = dec11_fileToGame("src/main/resources/dec11_sample5.txt")

//        assertEquals(frame1, dec11_calculateNextStep_part1(frame0))
//        assertEquals(frame2, dec11_calculateNextStep_part1(frame1))
//        assertEquals(frame3, dec11_calculateNextStep_part1(frame2))
//        assertEquals(frame4, dec11_calculateNextStep_part1(frame3))
//        assertEquals(frame5, dec11_calculateNextStep_part1(frame4))
    }

    @Test
    fun dec11_calculateChairNorth_true() {
            val game = arrayListOf<List<MAP_TILE>>(
                arrayListOf(MAP_TILE.OCCUPIED),
                arrayListOf(MAP_TILE.FLOOR),
                arrayListOf(MAP_TILE.EMPTY)
            )

        assertTrue(dec11_calculateChairNorth(game, Pair(2,0)))
    }

    @Test
    fun dec11_calculateChairNorth_false() {
        val game = arrayListOf<List<MAP_TILE>>(
            arrayListOf(MAP_TILE.EMPTY),
            arrayListOf(MAP_TILE.FLOOR),
            arrayListOf(MAP_TILE.EMPTY)
        )
        assertFalse(dec11_calculateChairNorth(game, Pair(2,0)))
    }

    @Test
    fun dec11_test_sample_part2() {
        val game = dec11_fileToGame("src/main/resources/dec11_sample_part2_true.txt")
        val coordinate = Pair(4,3)
        assertEquals(MAP_TILE.EMPTY, game[coordinate.first][coordinate.second])

        assertTrue(dec11_calculateChairNorth(game, coordinate))
        assertTrue(dec11_calculateChairEast(game, coordinate))
        assertTrue(dec11_calculateChairSouth(game, coordinate))
        assertTrue(dec11_calculateChairWest(game, coordinate))

        assertTrue(dec11_calculateChairNorthWest(game, coordinate))
        assertTrue(dec11_calculateChairNorthEast(game, coordinate))

        assertTrue(dec11_calculateChairSouthWest(game, coordinate))
        assertTrue(dec11_calculateChairSouthEast(game, coordinate))

    }

    @Test
    fun dec11_test_sample_part2_false() {
        val game = dec11_fileToGame("src/main/resources/dec11_sample_part2_false.txt")
        val coordinate = Pair(3,3)
        assertEquals(MAP_TILE.EMPTY, game[coordinate.first][coordinate.second])

        assertFalse(dec11_calculateChairNorth(game, coordinate))
        assertFalse(dec11_calculateChairEast(game, coordinate))
        assertFalse(dec11_calculateChairSouth(game, coordinate))
        assertFalse(dec11_calculateChairWest(game, coordinate))

        assertFalse(dec11_calculateChairNorthWest(game, coordinate))
        assertFalse(dec11_calculateChairNorthEast(game, coordinate))

        assertFalse(dec11_calculateChairSouthWest(game, coordinate))
        assertFalse(dec11_calculateChairSouthEast(game, coordinate))

    }

    @Test
    fun dec11_test_sample_part2_max() {
        val game = dec11_fileToGame("src/main/resources/dec11_sample_part2_true_max.txt")
        val coordinate = Pair(2,2)
        assertEquals(MAP_TILE.EMPTY, game[coordinate.first][coordinate.second])

        assertTrue(dec11_calculateChairNorth(game, coordinate))
        assertTrue(dec11_calculateChairEast(game, coordinate))
        assertTrue(dec11_calculateChairSouth(game, coordinate))
        assertTrue(dec11_calculateChairWest(game, coordinate))

        assertTrue(dec11_calculateChairNorthWest(game, coordinate))
        assertTrue(dec11_calculateChairNorthEast(game, coordinate))

        assertTrue(dec11_calculateChairSouthWest(game, coordinate))
        assertTrue(dec11_calculateChairSouthEast(game, coordinate))

    }

    @Test
    fun dec11_testNextFrames() {
        val frame0 = dec11_fileToGame("src/main/resources/dec11_sample_part2_frame0.txt")
        val frame1 = dec11_fileToGame("src/main/resources/dec11_sample_part2_frame1.txt")
        val frame2 = dec11_fileToGame("src/main/resources/dec11_sample_part2_frame2.txt")
        val frame3 = dec11_fileToGame("src/main/resources/dec11_sample_part2_frame3.txt")
        val frame4 = dec11_fileToGame("src/main/resources/dec11_sample_part2_frame4.txt")
        val frame5 = dec11_fileToGame("src/main/resources/dec11_sample_part2_frame5.txt")
        val frame6 = dec11_fileToGame("src/main/resources/dec11_sample_part2_frame6.txt")

        assertEquals(frame1, dec11_calculateNextStep_part2(frame0))
        assertEquals(frame2, dec11_calculateNextStep_part2(frame1))
        assertEquals(frame3, dec11_calculateNextStep_part2(frame2))
        assertEquals(frame4, dec11_calculateNextStep_part2(frame3))
        assertEquals(frame5, dec11_calculateNextStep_part2(frame4))
        assertEquals(frame6, dec11_calculateNextStep_part2(frame5))

        assertEquals(26, dec11_calculateTotalSeatsTaken(frame6))
    }

}

