import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Dec22ndTest {

   @Test
   fun dec22_readFile() {
       val output = dec22_readFile("src/main/resources/dec22_sample.txt")

       assertEquals(5, output.first.size)
       assertEquals(5, output.second.size)

       val firstRound = dec22_processRound(output.first, output.second)
       assertEquals(6, firstRound.first.size)
       assertEquals(4, firstRound.second.size)

       val finalTotal = dec22_playGame(output)
       assertEquals(306, finalTotal)

   }
}

