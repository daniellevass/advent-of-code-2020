import java.io.File
import java.lang.Exception
import kotlin.math.pow

fun main(args: Array<String>) {

    var game = dec11_fileToGame("src/main/resources/dec11_input.txt")
    var previousSeatsTaken = 0
    var previousSeatsTakenCount = 0
    var completed = false

    while(!completed) {
        // calculate next frame
        val nextFrame = dec11_calculateNextStep_part2(game)
        val seatsTaken = dec11_calculateTotalSeatsTaken(nextFrame)

        // is the number of seats the same
        if (seatsTaken == previousSeatsTaken) {
            previousSeatsTakenCount ++

            if (previousSeatsTakenCount == 100) {
                completed = true
            }

        } else {
            previousSeatsTakenCount = 0
        }

        // set the variables for the next loop
        previousSeatsTaken = seatsTaken
        game = nextFrame
    }

    println("we had $previousSeatsTaken seats taken")
}

enum class MAP_TILE {
    EMPTY, OCCUPIED, FLOOR
}

fun dec11_fileToGame(filename: String) : List<List<MAP_TILE>> {
    val input = ArrayList<ArrayList<MAP_TILE>>()
    // get the input
    File(filename)
        .forEachLine {
            val parts = it.split("")
            val row = ArrayList<MAP_TILE>()
            for (char in parts) {
                when (char) {
                    "." -> row.add(MAP_TILE.FLOOR)
                    "L" -> row.add(MAP_TILE.EMPTY)
                    "#" -> row.add(MAP_TILE.OCCUPIED)
                }
            }
            input.add(row)
        }

    return input
}

fun dec11_calculateTotalSeatsTaken(game: List<List<MAP_TILE>>) : Int {
    var seatsOccupied = 0

    for (row in game) {
        for (cell in row) {
            if (cell == MAP_TILE.OCCUPIED) seatsOccupied++
        }
    }
    return seatsOccupied
}

//fun dec11_calculateNextStep_part1(game: List<List<MAP_TILE>>) : List<List<MAP_TILE>>{
//    val nextFrame = ArrayList<ArrayList<MAP_TILE>>()
//
//    for(x in game.indices step 1) {
//        val row = game[x]
//        val newRow = ArrayList<MAP_TILE>()
//        for(y in row.indices step 1) {
//            newRow.add(dec11_calculateWhatShouldHappenToCell(game, Pair(x, y)))
//        }
//        nextFrame.add(newRow)
//    }
//
//    return nextFrame
//}

fun dec11_calculateNextStep_part2(game: List<List<MAP_TILE>>) : List<List<MAP_TILE>>{
    val nextFrame = ArrayList<ArrayList<MAP_TILE>>()

    for(x in game.indices step 1) {
        val row = game[x]
        val newRow = ArrayList<MAP_TILE>()
        for(y in row.indices step 1) {
            newRow.add(dec11_calculateWhatShouldHappenToCell_part2(game, Pair(x, y)))
        }
        nextFrame.add(newRow)
    }

    return nextFrame
}

//fun dec11_calculateWhatShouldHappenToCell(game: List<List<MAP_TILE>>, coordinate: Pair<Int, Int>) : MAP_TILE {
//    val currentTile = game[coordinate.first][coordinate.second]
//
//    // if we're a floor tile, floor tiles don't change
//    if (currentTile == MAP_TILE.FLOOR) return MAP_TILE.FLOOR
//
//    // calculate adjacent seats
//    val seatsOccupiedAround = dec11_calculateCellsOccupiedAroundCell(game, coordinate)
//
//    // if seat is empty and no seats around it are taken -> become occupied
//    if (currentTile == MAP_TILE.EMPTY && seatsOccupiedAround == 0) {
//        return MAP_TILE.OCCUPIED
//    }
//
//    // if seat is occupied and 4 seats or more are around it -> become empty
//    if (currentTile == MAP_TILE.OCCUPIED && seatsOccupiedAround >= 4) {
//        return MAP_TILE.EMPTY
//    }
//
//    // seat didn't change
//    return currentTile
//}

fun dec11_calculateWhatShouldHappenToCell_part2(game: List<List<MAP_TILE>>, coordinate: Pair<Int, Int>) : MAP_TILE {
    val currentTile = game[coordinate.first][coordinate.second]

    // if we're a floor tile, floor tiles don't change
    if (currentTile == MAP_TILE.FLOOR) return MAP_TILE.FLOOR

    // calculate adjacent seats
    var seatsOccupiedAround = 0
    if (dec11_calculateChairNorth(game, coordinate)) seatsOccupiedAround ++
    if (dec11_calculateChairEast(game, coordinate)) seatsOccupiedAround ++
    if (dec11_calculateChairSouth(game, coordinate)) seatsOccupiedAround ++
    if (dec11_calculateChairWest(game, coordinate)) seatsOccupiedAround ++

    if (dec11_calculateChairNorthEast(game, coordinate)) seatsOccupiedAround ++
    if (dec11_calculateChairSouthEast(game, coordinate)) seatsOccupiedAround ++
    if (dec11_calculateChairSouthWest(game, coordinate)) seatsOccupiedAround ++
    if (dec11_calculateChairNorthWest(game, coordinate)) seatsOccupiedAround ++

    // if seat is empty and no seats around it are taken -> become occupied
    if (currentTile == MAP_TILE.EMPTY && seatsOccupiedAround == 0) {
        return MAP_TILE.OCCUPIED
    }

    //  people seem to be more tolerant than you expected: it now takes five or more visible
    //  occupied seats for an occupied seat to become empty
    if (currentTile == MAP_TILE.OCCUPIED && seatsOccupiedAround >= 5) {
        return MAP_TILE.EMPTY
    }

    // seat didn't change
    return currentTile
}

fun dec11_calculateCellsOccupiedAroundCell(game: List<List<MAP_TILE>>, coordinate: Pair<Int, Int>) : Int {
    var chairsOccupied = 0

    //look at the row above current cell
    if (coordinate.first != 0) {
        if (coordinate.second != 0) {
            chairsOccupied += if (game[coordinate.first - 1][coordinate.second - 1] == MAP_TILE.OCCUPIED) 1 else 0
        }
        chairsOccupied += if (game[coordinate.first - 1][coordinate.second] == MAP_TILE.OCCUPIED) 1 else 0
        if (coordinate.second < game[0].size-1) {
            chairsOccupied += if (game[coordinate.first - 1][coordinate.second + 1] == MAP_TILE.OCCUPIED) 1 else 0
        }
    }

    //look at the row below current cell
    if (coordinate.first < game.size -1) {
        if (coordinate.second != 0) {
            chairsOccupied += if (game[coordinate.first + 1][coordinate.second - 1] == MAP_TILE.OCCUPIED) 1 else 0
        }
        chairsOccupied += if (game[coordinate.first + 1][coordinate.second] == MAP_TILE.OCCUPIED) 1 else 0
        if (coordinate.second < game[0].size -1) {
            chairsOccupied += if (game[coordinate.first + 1][coordinate.second + 1] == MAP_TILE.OCCUPIED) 1 else 0
        }
    }

    //look at the two cells to the left and right
    if (coordinate.second != 0) {
        chairsOccupied += if (game[coordinate.first][coordinate.second - 1] == MAP_TILE.OCCUPIED) 1 else 0
    }
    if (coordinate.second < game[0].size -1) {
        chairsOccupied += if (game[coordinate.first][coordinate.second + 1] == MAP_TILE.OCCUPIED) 1 else 0
    }
    return chairsOccupied
}

fun dec11_calculateCellsOccupiedAroundcell_part2(game:List<List<MAP_TILE>>, coordinate: Pair<Int, Int>) : Int {
    var chairsOccupied = 0

    // check in each direction

    return chairsOccupied
}

fun dec11_calculateChairNorth(game:List<List<MAP_TILE>>, coordinate: Pair<Int, Int>) : Boolean {
    // if we can't go further north, then just return nothing here
    if (coordinate.first == 0) {
        return false
    }

    var x = coordinate.first -1
    while (x >= 0) {
        // return on the first instance of these
        if (game[x][coordinate.second] == MAP_TILE.OCCUPIED) {
            return true
        } else if (game[x][coordinate.second] == MAP_TILE.EMPTY) {
            return false
        }
        // otherwise continue in that direction
        x--
    }
    return false
}

fun dec11_calculateChairSouth(game:List<List<MAP_TILE>>, coordinate: Pair<Int, Int>) : Boolean {
    // check if we're already at the boundary
    if (coordinate.first == game.size -1) {
        return false
    }

    var x = coordinate.first + 1
    while (x < game.size) {
        if (game[x][coordinate.second] == MAP_TILE.OCCUPIED) {
            return true
        } else if (game[x][coordinate.second] == MAP_TILE.EMPTY) {
            return false
        }
        x++
    }

    return false
}

fun dec11_calculateChairEast(game: List<List<MAP_TILE>>, coordinate: Pair<Int, Int>) : Boolean {
    // calculate boundary
    if (coordinate.second == (game[0].size -1)) {
        return false
    }

    var y = coordinate.second +1
    while (y < game[0].size) {
        if (game[coordinate.first][y] == MAP_TILE.OCCUPIED) {
            return true
        } else if (game[coordinate.first][y] == MAP_TILE.EMPTY) {
            return false
        }
        y++
    }
    
    return false
}

fun dec11_calculateChairWest(game: List<List<MAP_TILE>>, coordinate: Pair<Int, Int>) : Boolean {
    // calculate boundary
    if (coordinate.second == 0) {
        return false
    }

    var y = coordinate.second -1
    while (y >= 0) {
        if (game[coordinate.first][y] == MAP_TILE.OCCUPIED) {
            return true
        } else if (game[coordinate.first][y] == MAP_TILE.EMPTY) {
            return false
        }
        y--
    }
    return false
}

fun dec11_calculateChairSouthEast(game: List<List<MAP_TILE>>, coordinate: Pair<Int, Int>) : Boolean {

    // boundary means either edge
    if (coordinate.first == game.size -1
        || coordinate.second == game[0].size -1) {
        return false
    }

    var x = coordinate.first +1
    var y = coordinate.second +1

    while (x < game.size && y < game[0].size) {
        if (game[x][y] == MAP_TILE.OCCUPIED) {
            return true
        } else if (game[x][y] == MAP_TILE.EMPTY) {
            return false
        }
        x++
        y++
    }

    return false
}

// more chairs down, more chairs left
fun dec11_calculateChairSouthWest(game: List<List<MAP_TILE>>, coordinate: Pair<Int, Int>) : Boolean {

    // boundary means either edge
    if (coordinate.first == game.size -1
        || coordinate.second == 0) {
        return false
    }

    var x = coordinate.first +1
    var y = coordinate.second -1

    while (x < game.size && y >= 0) {
        if (game[x][y] == MAP_TILE.OCCUPIED) {
            return true
        } else if (game[x][y] == MAP_TILE.EMPTY) {
            return false
        }
        x++
        y--
    }

    return false
}

// more chairs up, more chairs right
fun dec11_calculateChairNorthEast(game: List<List<MAP_TILE>>, coordinate: Pair<Int, Int>) : Boolean {

    // boundary means either edge
    if (coordinate.first == 0
        || coordinate.second == game.size -1 ) {
        return false
    }

    var x = coordinate.first -1
    var y = coordinate.second +1

    while (x >= 0 && y < game[0].size) {
        if (game[x][y] == MAP_TILE.OCCUPIED) {
            return true
        } else if (game[x][y] == MAP_TILE.EMPTY) {
            return false
        }
        x--
        y++
    }

    return false
}

fun dec11_calculateChairNorthWest(game: List<List<MAP_TILE>>, coordinate: Pair<Int, Int>) : Boolean {

    // boundary means either edge
    if (coordinate.first == 0
        || coordinate.second == 0) {
        return false
    }

    var x = coordinate.first -1
    var y = coordinate.second -1

    while (x >= 0 && y >= 0) {
        if (game[x][y] == MAP_TILE.OCCUPIED) {
            return true
        } else if (game[x][y] == MAP_TILE.EMPTY) {
            return false
        }
        x--
        y--
    }

    return false
}

