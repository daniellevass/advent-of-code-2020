import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Dec23rdTest {

  @Test
  fun dec23_readInput() {
      val input = "389125467"
      val output = dec23_readInput(input)

      assertEquals(9, output.size)

      var game = output.toList()
      for (i in 1..10) {
          println("round $i")
          val newGame = dec23_processRound_part2(game)
          game = newGame
      }
    // round 10 = [8, 3, 7, 4, 1, 9, 2, 6, 5]
      // should be each number except 1 should appear exactly once.
  // In the above example, after 10 moves,
      // the cups clockwise from 1 are labeled 9, 2, 6, 5, and so on, producing 92658374. 92658374

      val conclusion = dec23_concludeRound(game)
      assertEquals("92658374", conclusion)
      println(conclusion)
  }

    @Test
    fun dec23_map_part1() {

        val input = "389125467"
        var game = dec23_map_readInput(input, false)

        assertEquals(9, game.size)

        for (i in 1..10) {
            println("round $i")
            val newGame = dec23_map_processRound(game)
            game = newGame
        }

        println(game)

    }

    @Test
    fun dec23_part2() {

        val input = "389125467"
        val cups = dec23_readInput(input)
//        val moreCups = dec23_part2_makeInput(initialCups)

        assertEquals(1_000_000, cups.size)

        /*
        the crab is going to do ten million (10000000) moves

        The crab is going to hide your stars - one each -
        under the two cups that will end up immediately clockwise of cup 1.
        You can have them if you predict what the labels on those cups will be when the crab is finished.

        In the above example (389125467), this would be 934001 and then 159792;
        multiplying these together produces 149245887792.
         */

        var game = cups
        for (i in 1..1_000) {

//            if (i%1_000 == 0) {
                println("got to $i")
//            }

            val newGame = dec23_processRound(game)
            game = newGame
        }
//
//        dec23_part2_conclude(game)

    }
}

