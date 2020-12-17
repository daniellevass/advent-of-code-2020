import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.asserter

class Dec16thTest {

    @Test
    fun dec16_readRule() {
        assertEquals(TicketRule("class", Pair(1,3), Pair(5,7)),
            dec16_readRule("class: 1-3 or 5-7"))
    }

    @Test
    fun dec16_readTicket() {
        assertEquals(arrayListOf(7,3,47),
            dec16_readTicket("7,3,47"))
    }

    @Test
    fun dec16_returnInvalidItemsForTicket() {
        val ticket = dec16_readTicket("40,4,50")
        val rules = arrayListOf<TicketRule>(
            dec16_readRule("class: 1-3 or 5-7"),
            dec16_readRule("row: 6-11 or 33-44"),
            dec16_readRule("seat: 13-40 or 45-50"),
        )

        assertEquals(arrayListOf(4), dec16_returnInvalidItemsForTicket(ticket, rules))
    }

}

