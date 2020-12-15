import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.asserter

class Dec15thKtTest {

    @Test
    fun dec15_part1() {

//        val game = arrayListOf(0, 3, 6)
//
//        val step4 = dec15_runGame(game, 3)
//        assertEquals(0, step4)
//        game.add(step4)
//
//        val step5 = dec15_runGame(game, 4)
//        assertEquals(3, step5)
//        game.add(step5)
//
//        val step6 = dec15_runGame(game, 5)
//        assertEquals(3, step6)
//        game.add(step6)
//
//        val step7 = dec15_runGame(game, 6)
//        assertEquals(1, step7)
//        game.add(step7)
//
//        val step8 = dec15_runGame(game, 7)
//        assertEquals(0, step8)
//        game.add(step8)
//
//        val step9 = dec15_runGame(game, 8)
//        assertEquals(4, step9)
//        game.add(step9)
//
//        val step10 = dec15_runGame(game, 9)
//        assertEquals(0, step10)
//        game.add(step10)

        // 4 -> 0 | 5 -> 3 | 6 -> 3 | 7 -> 1 | 8 -> 0 | 9 -> 4 | 10 -> 0
//        assertEquals(0, dec15_runGame(arrayListOf(0, 3, 6), 10))
        assertEquals(436, dec15_runGame(arrayListOf(0, 3, 6), 2020))

    }

//    @Test
//    fun dec15_runGame_1() {
//        assertEquals(1, dec15_runGame(arrayListOf(1, 3, 2), 2020 ))
//    }

//    @Test
//    fun dec15_runGame_1_part2() {
////        val gameLength = 30_000_000
////        assertEquals(175594, dec15_runGame(arrayListOf(1, 3, 2), gameLength ))
//    }

    @Test
    fun dec15_runGame_2() {
        assertEquals(10, dec15_runGame(arrayListOf(2,1,3), 2020 ))
    }

    @Test
    fun dec15_runGame_3() {
        assertEquals(27, dec15_runGame(arrayListOf(1,2,3), 2020 ))
    }

    @Test
    fun dec15_runGame_part1() {
        assertEquals(758, dec15_runGame(arrayListOf(2,20,0,4,1,17), 2020 ))
    }

    @Test
    fun dec15_runGame_part2() {
        val count = 30000000
        assertEquals(1, dec15_runGame(arrayListOf(2,20,0,4,1,17), count ))
    }

}

