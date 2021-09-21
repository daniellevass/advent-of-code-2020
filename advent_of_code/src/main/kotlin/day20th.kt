import java.io.File
import kotlin.math.sqrt

fun main(args: Array<String>) {

    val tiles = dec20_readFile("src/main/resources/dec20_input.txt")

    // loop through all the tiles
    for (tile1 in tiles) {
        for (tile2 in tiles) {

            // ignore if they're the same tile
            if (tile1.key != tile2.key) {
                if (dec20_determineIfMatch(tile1, tile2)) {
                    tile1.matches.add(tile2.key)
                }
            }
        }
    }

    // how many matches
    val corners = tiles.filter { it.matches.size == 2 }
    val height = sqrt(tiles.size.toFloat()).toInt()
    println("height = $height")

    val completeGrid = ArrayList<Triple<Int, Int, Dec20Shape?>>()
    // add in empty arrays for the rows

    // pick the first corner
    var nextTile = corners[1]

    completeGrid.add(Triple(0,0 , nextTile))
    tiles.remove(nextTile)
    println("starting with ${nextTile.key}" )

    // find the rest of the first row
    for (i in 1 until height) {
        val foundTile = dec20_findMatchEdge(nextTile, tiles.filter { nextTile.matches.contains(it.key) }, 1, 3)
        completeGrid.add(Triple(i, 0, foundTile!!))
        tiles.remove(foundTile)
        println("we got ${foundTile.key}")
        nextTile = foundTile
    }

    // find the rest of the rows
    for (j in 1 until height) {
        // get 0th item
        val indexPreviousItem = (j * height) - height
        val previousTile = completeGrid[indexPreviousItem].third
        val startNextRow = dec20_findMatchEdge(previousTile!!, tiles.filter { previousTile.matches.contains(it.key) }, 2, 0)
        completeGrid.add(Triple(0,j , startNextRow!!))
        tiles.remove(startNextRow)
        println("new row starts with ${startNextRow!!.key}")
        nextTile = startNextRow

        for (i in 1 until height) {
            val foundTile = dec20_findMatchEdge(nextTile, tiles.filter { nextTile.matches.contains(it.key) }, 1, 3)
            completeGrid.add(Triple(i, j, null))
//            tiles.remove(foundTile)
//            println("$i $j got ${foundTile.key}")
//            nextTile = foundTile
        }
    }

}

data class Dec20Shape(
    val key: Int,
    val edges :List<String>,
    val matches: ArrayList<Int> = ArrayList(),
    val content: List<String>)

fun dec20_readFile(filename: String) : ArrayList<Dec20Shape> {

    val tiles = ArrayList<Dec20Shape>()

    val currentTile = ArrayList<String>()
    File(filename)
        .forEachLine {
            if (it.isBlank()) {
                val tile = dec20_processTile(currentTile)
                tiles.add(tile)
                currentTile.clear()
            } else {
                currentTile.add(it)
            }
        }

    // add the last tile
    val tile = dec20_processTile(currentTile)
    tiles.add(tile)

    return tiles
}

fun dec20_processTile(tile :List<String>) : Dec20Shape {

    val key = tile[0].subSequence(5, tile[0].length-1).toString()

    val topEdge = tile[1]
    val bottomEdge = tile[10]
    var leftEdge = ""
    var rightEdge = ""

    val content = ArrayList<String>()

    for (i in 1 until tile.size) {
        val row = tile[i]

        leftEdge += row.substring(0, 1)
        rightEdge += row.substring(9, 10)
        content.add(row.substring(1,9))

    }

    // remove the last row of the content
    content.removeLast()

    return Dec20Shape(
        key = key.toInt(),
        edges = arrayListOf(topEdge, rightEdge, bottomEdge, leftEdge),
        content = content.toList()
    )
}

fun dec20_determineIfMatch(tile1: Dec20Shape, tile2: Dec20Shape) : Boolean {
    // do any of my edges match any edge on any other tile
    // what if each of my edges are flipped
    for (edge1 in tile1.edges) {
        for(edge2 in tile2.edges) {
            if (edge1 == edge2 || edge1.reversed() == edge2) {
                return true
            }
        }
    }

    return false
}

fun dec20_findMatchEdge(tile1: Dec20Shape, options: List<Dec20Shape>,
                        sourceEdge: Int, destinationEdge: Int) : Dec20Shape? {

    var foundShape:Dec20Shape? = null

    for (option in options) {

        if (tile1.edges[sourceEdge] == option.edges[destinationEdge]) {
            foundShape = option
        }

        val reversedOption = dec20_reverseShape(option)
        if (tile1.edges[sourceEdge] == reversedOption.edges[destinationEdge]) {
            foundShape = reversedOption
        }

        var orientedShape = dec20_orientShape90(option)
        for (i in 0..4) {

            if (tile1.edges[sourceEdge] == orientedShape.edges[destinationEdge]) {
                foundShape = orientedShape
            }

            val reversedOriented = dec20_reverseShape(orientedShape)
            if (tile1.edges[sourceEdge] == reversedOriented.edges[destinationEdge]) {
                foundShape = reversedOriented
            }

            // shift it again
            val newOrientatedShape = dec20_orientShape90(orientedShape)
            orientedShape =  newOrientatedShape
        }

    }

    return foundShape
}

fun dec20_reverseShape(tile: Dec20Shape) : Dec20Shape {
    val reversedEdges = ArrayList<String>()
    for (edge in tile.edges) {
        reversedEdges.add(edge.reversed())
    }

    val reversedContent = ArrayList<String>()
    for (content in tile.content) {
        reversedContent.add(content.reversed())
    }

    return Dec20Shape(
        key = tile.key,
        edges = reversedEdges,
        matches = tile.matches,
        content = reversedContent
    )
}

fun dec20_orientShape90(tile: Dec20Shape) : Dec20Shape {

    val orientatedContent = ArrayList<String>()

    // get all the 0 elements into a string
    for (i in tile.content.first().indices) {
        var row = ""
        for (j in tile.content.indices) {
            row += tile.content[j][i]
        }
        orientatedContent.add(row.reversed())
    }

    val edges = ArrayList<String>()
    edges.add(tile.edges[3].reversed())
    edges.add(tile.edges[0])
    edges.add(tile.edges[1].reversed())
    edges.add(tile.edges[2])

    return Dec20Shape(
        key = tile.key,
        edges = edges,
        matches = tile.matches,
        content = orientatedContent
    )
}