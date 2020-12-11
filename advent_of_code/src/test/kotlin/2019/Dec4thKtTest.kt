package `2019`

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Dec4thKtTest {

   @Test
   fun validate_one() {
       assertTrue(dec9_validateNumber(111111))
   }

    @Test
    fun validate_two() {
        assertFalse(dec9_validateNumber(223450))
    }

    @Test
    fun validate_three() {
        assertFalse(dec9_validateNumber(123789))
    }
}

