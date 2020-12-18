import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main(args: Array<String>) {

    dec17_part2()

}

fun dec17_part2()  {

    // start with a simple x,y frame
    val child: GameOfLifeChild = dec17_part2_readInput("src/main/resources/dec17/dec17_input.txt")

    // wrap the child in a hashmap for i, and a hashmap for w
    var game = hashMapOf(0 to hashMapOf(0 to child))

    for (count in 1..6) {
        val nextCycle = dec17_part2_calculateNextCycle(game)
        game = nextCycle
        println("we had w=${game.size} - i=${game[0]!!.size}")
        val sum = dec17_countAlive(game)
        println("$sum alive")
    }
}

fun dec17_countAlive(game: HashMap<Int, HashMap<Int, GameOfLifeChild>>) : Int {
    var sum = 0
    for (w in game.keys) {
        for (i in game[w]!!.keys) {
            sum += game[w]!![i]!!.alive
        }
    }
    return sum
}

data class GameOfLifeFrame(
    var map: Map<Int, Map<Int, Boolean>> = HashMap(),
    val index: Int,
    var countAlive: Int = 0)

data class GameOfLifeChild (
    var map: HashMap<Int, Map<Int, Boolean>> = HashMap(),
    var alive: Int = 0
)

fun dec17_part1() {
    var game = listOf(dec17_readInput("src/main/resources/dec17/dec17_input.txt"))

    for (i in 1..6) {
        val nextCycle = dec17_part1_calculateNextCycle(game)
        game = nextCycle
    }

    // count all the alive
    var sum = 0
    for (frame in game) {
        sum += frame.countAlive
    }

    println("we had $sum alive at the end of 6 cycles")
}

fun dec17_part1_calculateNextCycle(game: List<GameOfLifeFrame>) : List<GameOfLifeFrame> {

    val nextGame = dec17_part1_createNewGame(game)

    val minX = Collections.min(game.first().map.keys) -1
    val maxX = Collections.max(game.first().map.keys) +1

    val minY = Collections.min(game.first().map[0]!!.keys) -1
    val maxY = Collections.max(game.first().map[0]!!.keys) +1

    // next game has new frames added, we need to use those to populate
    for (frame in nextGame) {

        var aliveCells = 0
        val map = HashMap<Int, Map<Int, Boolean>>()

        for (x in minX..maxX) {

            val row = HashMap<Int, Boolean>()

            for(y in minY..maxY) {

                val cellsAliveNearby = dec17_part1_countNodesAliveAtCoords(frame.index, x, y, game)

                val gameFrame = dec17_part1_getFrameForIndex(frame.index, game)
                if (gameFrame?.map?.get(x)?.get(y) == true) {
                    // cell was alive
                    // If a cube is active and exactly 2 or 3 of its neighbors are also active,
                    //the cube remains active. Otherwise, the cube becomes inactive.
                    if (cellsAliveNearby == 2 || cellsAliveNearby == 3) {
                        row[y] = true
                        aliveCells ++
                        println("i=${frame.index}, x=$x, y=$y stays alive with $cellsAliveNearby alive nearby")
                    } else {
                        row[y] = false
                        println("i=${frame.index}, x=$x, y=$y dies with $cellsAliveNearby alive nearby")
                    }

                } else {
                    // cell was dead
                    //If a cube is inactive but exactly 3 of its neighbors are active,
                    //the cube becomes active. Otherwise, the cube remains inactive.

                    if (cellsAliveNearby == 3) {
                        row[y] = true
                        aliveCells ++
                        println("i=${frame.index}, x=$x, y=$y becomes alive with $cellsAliveNearby alive nearby")
                    } else {
                        row[y] = false
                        println("i=${frame.index}, x=$x, y=$y still dead with $cellsAliveNearby alive nearby")
                    }
                }
            }
            map[x] = row
        }

        frame.map = map
        frame.countAlive = aliveCells
    }

    // end strip off any tops, sides, and bottoms that don't have any alive cells?
    return nextGame
}


fun dec17_part2_calculateNextCycle(game: HashMap<Int, HashMap<Int, GameOfLifeChild>>) : HashMap<Int, HashMap<Int, GameOfLifeChild>> {

    val nextGame = dec17_part2_createNewCycle(game)

    val sampleItem = game[0]!![0]!!.map
    val minX = Collections.min(sampleItem.keys) -1
    val maxX = Collections.max(sampleItem.keys) +1

    val minY = Collections.min(sampleItem[0]!!.keys) -1
    val maxY = Collections.max(sampleItem[0]!!.keys) +1

    for (w in nextGame.keys) {
        for (i in nextGame.keys) {
            val map = HashMap<Int, Map<Int, Boolean>>()
            var aliveCells = 0

            for (x in minX..maxX) {
                val row = HashMap<Int, Boolean>()
                for (y in minY..maxY) {

                    val cellsAliveNearby = dec17_part2_countNodesAliveAtCoords(w, i, x, y, game)
                    val me = game[w]?.get(i)?.map?.get(x)?.get(y)
                    if (me != null && me == true) {
                        // cell was alive
                        // If a cube is active and exactly 2 or 3 of its neighbors are also active,
                        //the cube remains active. Otherwise, the cube becomes inactive.
                        if (cellsAliveNearby == 2 || cellsAliveNearby == 3) {
                            row[y] = true
                            aliveCells ++
                        } else {
                            row[y] = false
                        }

                    } else {
                        // cell was dead
                        //If a cube is inactive but exactly 3 of its neighbors are active,
                        //the cube becomes active. Otherwise, the cube remains inactive.
                        if (cellsAliveNearby == 3) {
                            row[y] = true
                            aliveCells ++
                        } else {
                            row[y] = false
                        }
                    }

                }
                map[x] = row
            }

            println("w=$w i=$i")
            nextGame[w]!![i]!!.map = map
            nextGame[w]!![i]!!.alive = aliveCells
        }
    }

    return nextGame
}

fun dec17_readInput(fileName: String) : GameOfLifeFrame{

    val frame = GameOfLifeFrame(index = 0)
    val map = HashMap<Int, Map<Int, Boolean>>()
    var currentRowIndex = 0
    var alive = 0
    //"src/main/resources/dec17_sample_0_0.txt"
    File(fileName)
        .forEachLine {

            val row = HashMap<Int, Boolean>()

            val inputParts = it.split("").filter { it.isNotEmpty() }
            for (i in inputParts.indices) {

                if (inputParts[i] == "#") {
                    row[i] = true
                    alive ++
                } else if (inputParts[i] == ".") {
                    row[i] = false
                }

            }

            map[currentRowIndex] = row
            currentRowIndex ++
        }

    frame.map = map
    frame.countAlive = alive

    return frame
}
fun dec17_part2_readInput(fileName: String) : GameOfLifeChild {

    val frame = GameOfLifeChild()
    val map = HashMap<Int, Map<Int, Boolean>>()
    var currentRowIndex = 0
    var alive = 0
    //"src/main/resources/dec17_sample_0_0.txt"
    File(fileName)
        .forEachLine {

            val row = HashMap<Int, Boolean>()

            val inputParts = it.split("").filter { it.isNotEmpty() }
            for (i in inputParts.indices) {

                if (inputParts[i] == "#") {
                    row[i] = true
                    alive ++
                } else if (inputParts[i] == ".") {
                    row[i] = false
                }

            }

            map[currentRowIndex] = row
            currentRowIndex ++
        }

    frame.map = map
    frame.alive = alive

    return frame
}


fun dec17_part1_countNodesAliveAtCoords(frameIndex: Int, x: Int, y: Int, game: List<GameOfLifeFrame>) : Int {

    var count = 0

    // we need to get the current frame and count around it
    val currentFrame: GameOfLifeFrame? = dec17_part1_getFrameForIndex(frameIndex, game)
    if (currentFrame != null) {

        if (currentFrame.map[x-1]?.get(y +1) == true) count++
        if (currentFrame.map[x-1]?.get(y) == true) count++
        if (currentFrame.map[x-1]?.get(y -1) == true) count++
        if (currentFrame.map[x]?.get(y +1) == true) count++
        if (currentFrame.map[x]?.get(y -1) == true) count++
        if (currentFrame.map[x+1]?.get(y +1) == true) count++
        if (currentFrame.map[x+1]?.get(y) == true) count++
        if (currentFrame.map[x+1]?.get(y -1) == true) count++

    }

    // get the frame above
    val aboveFrame: GameOfLifeFrame? = dec17_part1_getFrameForIndex(frameIndex +1, game)
    if (aboveFrame != null) {
        if (aboveFrame.map[x-1]?.get(y +1) == true) count++
        if (aboveFrame.map[x-1]?.get(y) == true) count++
        if (aboveFrame.map[x-1]?.get(y -1) == true) count++
        if (aboveFrame.map[x]?.get(y +1) == true) count++
        if (aboveFrame.map[x]?.get(y) == true) count++
        if (aboveFrame.map[x]?.get(y -1) == true) count++
        if (aboveFrame.map[x+1]?.get(y +1) == true) count++
        if (aboveFrame.map[x+1]?.get(y) == true) count++
        if (aboveFrame.map[x+1]?.get(y -1) == true) count++
    }

    // get the frame below
    val belowFrame: GameOfLifeFrame? = dec17_part1_getFrameForIndex(frameIndex -1, game)
    if (belowFrame != null) {
        if (belowFrame.map[x-1]?.get(y +1) == true) count++
        if (belowFrame.map[x-1]?.get(y) == true) count++
        if (belowFrame.map[x-1]?.get(y -1) == true) count++
        if (belowFrame.map[x]?.get(y +1) == true) count++
        if (belowFrame.map[x]?.get(y) == true) count++
        if (belowFrame.map[x]?.get(y -1) == true) count++
        if (belowFrame.map[x+1]?.get(y +1) == true) count++
        if (belowFrame.map[x+1]?.get(y) == true) count++
        if (belowFrame.map[x+1]?.get(y -1) == true) count++
    }

    return count
}

fun dec17_part1_getFrameForIndex(index: Int, game: List<GameOfLifeFrame> ) : GameOfLifeFrame? {
    return game.find { it.index == index }
}

fun dec17_part1_createNewGame(game: List<GameOfLifeFrame>) : List<GameOfLifeFrame>{
    val clearNextFrame = ArrayList<GameOfLifeFrame>()

    // only need to add a new frame at the start if we have 2 or more alive in the current start frame
    val firstIndex = game.first().index
    if (game.first().countAlive > 1) {
        clearNextFrame.add(GameOfLifeFrame(index = (firstIndex -1)))
    }

    // add all the other frames in
    for(frame in game) {
        clearNextFrame.add(GameOfLifeFrame(index = frame.index))
    }

    // only need to add a new frame at the end if we have 2 or more alive in the current end frame
    val lastIndex = game.last().index
    if (game.last().countAlive > 1) {
        clearNextFrame.add(GameOfLifeFrame(index = lastIndex +1))
    }

    return clearNextFrame.sortedBy { it.index }
}

fun dec17_part2_createNewCycle(game: Map<Int, Map<Int, GameOfLifeChild>>) :HashMap<Int, HashMap<Int, GameOfLifeChild>> {

    val clearGame = HashMap<Int, HashMap<Int, GameOfLifeChild>>()

    for (wKey in game.keys) {
        clearGame[wKey] = dec17_part2_newIFrames(game[0]!!)
    }

    val minW = Collections.min(game.keys)
    val maxW = Collections.max(game.keys)

    clearGame[minW -1] = dec17_part2_newIFrames(game[0]!!)
    clearGame[maxW +1] = dec17_part2_newIFrames(game[0]!!)

    return clearGame
}

fun dec17_part2_newIFrames(iGame: Map<Int, GameOfLifeChild>) : HashMap<Int, GameOfLifeChild> {

    val newIgame = HashMap<Int, GameOfLifeChild>()

    // add new i nodes
    // get the current biggest i

    val minI = Collections.min(iGame.keys)
    val maxI = Collections.max(iGame.keys)

    if (iGame[maxI]!!.alive > 2) {
        newIgame[maxI + 1] = GameOfLifeChild()
    }

    // add the middle nodes
    for (key in iGame.keys) {
        newIgame[key] = GameOfLifeChild()
    }

    // get the current smallest i
    if (iGame[minI]!!.alive > 2) {
        newIgame[minI - 1] = GameOfLifeChild()
    }

    return newIgame
}

fun dec17_part2_countNodesAliveAtCoords(w: Int, i: Int, x: Int, y: Int,
                                        game: HashMap<Int, HashMap<Int, GameOfLifeChild>>) : Int {
    var neighboursAlive = 0
    // ach cube only ever considers its neighbors:
    // any of the 80 other cubes where any of their coordinates differ by at most 1.

    for (a in w-1..w+1){
        for (b in i-1..i+1) {
            for (c in x-1..x+1) {
                for (d in y-1..y+1) {
                    val item = game[a]?.get(b)?.map?.get(c)?.get(d)
                    if (item != null && item == true) {
                        neighboursAlive ++
                    }
                }
            }
        }
    }

    // we accidentally counted ourself so, if we're alive, let's remove us from our list
    val myState = game[w]?.get(i)?.map?.get(x)?.get(y)
    if (myState != null && myState == true) {
        neighboursAlive --
    }


    return neighboursAlive
}
