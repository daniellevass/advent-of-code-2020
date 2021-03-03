import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Dec19thTest {

   @Test
   fun dec19_readFile() {
       val output = dec19_readFile("src/main/resources/dec19_sample.txt")

       assertEquals(6, output.first.size) // 6 rules
       assertEquals(5, output.second.size) // 5 messages

       assertEquals("4 1 5", output.first[0]!!) // 0th rule should be
       assertEquals("ababbb", output.second[0])

       val node0 = dec19_formatRules(output.first)
       assertEquals(3, node0.childrenA.size)
       assertEquals(0, node0.childrenB.size)

       val finalRules = dec19_createFullRuleSet(node0)
       assertEquals(4, finalRules.size)

   }
}

