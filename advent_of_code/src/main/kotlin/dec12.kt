import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {

    var world = World(
        ship =CurrentLocation("E",  Pair(0,0)),
        waypoint = CurrentLocation("N", Pair(10,1)))

    File("src/main/resources/dec12_input.txt")
        .forEachLine {
            var newWorld = dec12_runInstruction(world, it)
            world = newWorld
        }

    println("we ended up at ${world.ship.direction} and ${world.ship.position}")
    val manhattanDistance = abs(world.ship.position.first) + abs(world.ship.position.second)
    println("manhattan distance = $manhattanDistance")
}

data class World (val ship: CurrentLocation, val waypoint: CurrentLocation)

data class CurrentLocation (val direction: String, val position: Pair<Int, Int>)

fun dec12_runInstruction(currentWorld: World, instruction: String) : World {
    // calculate what it's asking us to do
    val direction = instruction.substring(0, 1)
    val length = Integer.parseInt(instruction.substring(1, instruction.length))

//    var shipDirection = currentWorld.ship.direction
    var shipX = currentWorld.ship.position.first
    var shipY = currentWorld.ship.position.second

    var waypointX = currentWorld.waypoint.position.first
    var waypointY = currentWorld.waypoint.position.second

    when(direction) {
        "N" -> { waypointY += length }
        "S" -> { waypointY -= length }
        "E" -> { waypointX += length }
        "W" -> { waypointX -= length }

        "L" -> {
            val tempLocation = dec12_part2_processRotation(currentWorld.waypoint, -length)
            waypointX = tempLocation.position.first
            waypointY = tempLocation.position.second
        }
        "R" -> {
            val tempLocation = dec12_part2_processRotation(currentWorld.waypoint, +length)
            waypointX = tempLocation.position.first
            waypointY = tempLocation.position.second
        }
        "F" -> {
            shipX += (waypointX * length)
            shipY += (waypointY * length)
        }
    }

    /**
     * R90 rotates the waypoint around the ship clockwise 90 degrees,
     * moving it to 4 units east and 10 units south of the ship. The ship remains at east 170, north 38.
     *            waypoint(4,-10) ship(170,38)
     * previously waypoint(10,4) ship(170,38)
     */

    return World(ship = CurrentLocation(currentWorld.ship.direction, Pair(shipX, shipY)),
        waypoint = CurrentLocation(currentWorld.waypoint.direction, Pair(waypointX, waypointY)))
}

fun dec12_part2_processRotation(waypoint: CurrentLocation, degrees: Int) : CurrentLocation {

    var currentWaypoint = waypoint

    if (degrees > 0) {
        // going right
        var i = degrees
        while (i > 0) {
            currentWaypoint = dec12_processRotationOneRight(currentWaypoint)
            i -=90
        }

    } else {
        //going left
        var i = degrees
        while (i < 0) {
            currentWaypoint = dec12_processRotationOneLeft(currentWaypoint)
            i +=90
        }
    }

    return currentWaypoint
}

fun dec12_processRotationOneRight(waypoint: CurrentLocation) : CurrentLocation {

    return CurrentLocation(waypoint.direction,
        Pair(waypoint.position.second, -waypoint.position.first))
}

fun dec12_processRotationOneLeft(waypoint: CurrentLocation) : CurrentLocation {
    return CurrentLocation(waypoint.direction,
        Pair(-waypoint.position.second, waypoint.position.first))
}

fun dec12_part1_processRotation(currentDirection: String, degrees: Int) : String {
    if (degrees == 0) return currentDirection // don't move if degrees is 0

    val directions = arrayListOf("N", "E", "S", "W")

    val currentIndex = directions.indexOf(currentDirection)
    val positionsToMove = degrees / 90 // e.g. 90 -> 1 position or 180 -> 2 positions or -90 -> -1 position

    var newIndex = currentIndex + positionsToMove

    // if new index is greater than 3 subtract 4
    if (newIndex >= 4) {
        newIndex -=4
    }

    // if new index is less than 0, add 4
    if (newIndex < 0) {
        newIndex +=4
    }

    return directions[newIndex]
}

