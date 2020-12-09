import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.asserter

class Dec8thKtTest {

    @Test
    fun dec8_processLine_positive() {
        assertEquals(
            Instruction(OPERATION.ACCUMULATOR, 1),
            dec8_processLine("acc +1"))
    }

    @Test
    fun dec8_processLine_negative() {
        assertEquals(
            Instruction(OPERATION.ACCUMULATOR, -1),
            dec8_processLine("acc -1"))
    }

    @Test
    fun dec8_processLine_negativeJump() {
        assertEquals(
            Instruction(OPERATION.JUMP, -3),
            dec8_processLine("jmp -3"))
    }

    @Test
    fun dec8_processLine_nop() {
        assertEquals(
            Instruction(OPERATION.NO_OPERATION, 0),
            dec8_processLine("nop +0"))
    }

}

