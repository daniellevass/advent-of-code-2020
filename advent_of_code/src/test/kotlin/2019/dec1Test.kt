import org.junit.Test
import kotlin.test.assertEquals

class Dec1KtTest {

    @Test
    fun dec_calculateFuel1() {
        assertEquals(2, dec1_calculateFuel("14"))
    }

    @Test
    fun dec_calculateFuel2() {
        assertEquals(654, dec1_calculateFuel("1969"))
    }

    @Test
    fun dec_calculateFuel3() {
        assertEquals(33583, dec1_calculateFuel("100756"))
    }

    @Test
    fun dec1_calculateFuelForFuel1() {
        assertEquals(966, dec1_calculateFuelForFuel(1969))
    }

}

