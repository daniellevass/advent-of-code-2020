import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Dec4thKtTest {

    @Test
    fun dec4th_validateBirthYear_valid() {
        assertTrue(dec4th_validateNumber("2002", 1920, 2002))
    }

    @Test
    fun dec4th_validateBirthYear_invalid_before() {
        assertFalse(dec4th_validateNumber("1919", 1920, 2002))
    }

    @Test
    fun dec4th_validateBirthYear_invalid_after() {
        assertFalse(dec4th_validateNumber("2003", 1920, 2002))
    }

    @Test
    fun dec4th_validateBirthYear_invalid_NaN() {
        assertFalse(dec4th_validateNumber("xyc", 1920, 2002))
    }

    @Test
    fun dec4th_validateHairColour_valid() {
        assertTrue(dec4th_validateHexColour("#ffcc00"))
    }

    @Test
    fun dec4th_validateHairColour_invalid_incorrectLength() {
        assertFalse(dec4th_validateHexColour("#ffcc0"))
    }

    @Test
    fun dec4th_validateHairColour_invalid_notHex() {
        assertFalse(dec4th_validateHexColour("potato"))
    }
}

