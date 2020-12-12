import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.asserter

class Dec12thKtTest {

    @Test
    fun dec12_processRotation_N() {
        assertEquals("S", dec12_part1_processRotation("N", 180))
    }

    @Test
    fun dec12_processRotation_N2() {
        assertEquals("W", dec12_part1_processRotation("N", -90))
    }

    @Test
    fun dec12_processRotation_S() {
        assertEquals("W", dec12_part1_processRotation("S", 90))
    }

    @Test
    fun dec12_processRotation_S2() {
        assertEquals("E", dec12_part1_processRotation("S", -90))
    }

    // part 1
//    @Test
//    fun dec12_runInstruction_sample() {
//        val currentLocation = CurrentLocation("E",  Pair(0,0))
//
//        val location1 = dec12_runInstruction(currentLocation, "F10")
//        assertEquals(CurrentLocation("E", Pair(10,0)), location1)
//
//        val location2 = dec12_runInstruction(location1, "N3")
//        assertEquals(CurrentLocation("E", Pair(10,3)), location2)
//
//        val location3 = dec12_runInstruction(location2, "F7")
//        assertEquals(CurrentLocation("E", Pair(17,3)), location3)
//
//        val location4 = dec12_runInstruction(location3, "R90")
//        assertEquals(CurrentLocation("S", Pair(17,3)), location4)
//
//        val location5 = dec12_runInstruction(location4, "F11")
//        assertEquals(CurrentLocation("S", Pair(17,-8)), location5)
//    }

    @Test
    fun dec12_runInstruction_part2() {
        val world = World(
            ship =CurrentLocation("E",  Pair(0,0)),
            waypoint = CurrentLocation("N", Pair(10,1)))

        //leaving the ship at east 100, north 10. The waypoint stays 10 units east and 1 unit north of the ship.
        val world1 = dec12_runInstruction(world, "F10")
        assertEquals( World(
                        ship = CurrentLocation("E",  Pair(100,10)),
                        waypoint = CurrentLocation("N", Pair(10,1))),
                     world1)

        //N3 moves the waypoint 3 units north to 10 units east and 4 units north of the ship.
        // The ship remains at east 100, north 10.
        val world2 = dec12_runInstruction(world1, "N3")
        assertEquals( World(
            ship = CurrentLocation("E",  Pair(100,10)),
            waypoint = CurrentLocation("N", Pair(10,4))),
            world2)

        //F7 moves the ship to the waypoint 7 times (a total of 70 units east and 28 units north),
        // leaving the ship at east 170, north 38.
        // The waypoint stays 10 units east and 4 units north of the ship.
        val world3 = dec12_runInstruction(world2, "F7")
        assertEquals( World(
            ship = CurrentLocation("E",  Pair(170,38)),
            waypoint = CurrentLocation("N", Pair(10,4))),
            world3)

        //R90 rotates the waypoint around the ship clockwise 90 degrees,
        // moving it to 4 units east and 10 units south of the ship.
        // The ship remains at east 170, north 38.
        val world4 = dec12_runInstruction(world3, "R90")
        assertEquals( World(
            ship = CurrentLocation("E",  Pair(170,38)),
            waypoint = CurrentLocation("N", Pair(4,-10))),
            world4)

        //F11 moves the ship to the waypoint 11 times (a total of 44 units east and 110 units south),
        // leaving the ship at east 214, south 72.
        // The waypoint stays 4 units east and 10 units south of the ship.
        val world5 = dec12_runInstruction(world4, "F11")
        assertEquals( World(
            ship = CurrentLocation("E",  Pair(214,-72)),
            waypoint = CurrentLocation("N", Pair(4,-10))),
            world5)

    }

    @Test
    fun dec12_part2_processRotation_1() {
        // given
        val world = World(
            ship =CurrentLocation("E",  Pair(0,0)),
            waypoint = CurrentLocation("N", Pair(10,1)))

        // when i move the waypoint R90
        val wayfair = dec12_part2_processRotation(world.waypoint, 90)

        assertEquals(Pair(1,-10), wayfair.position)
    }

}

