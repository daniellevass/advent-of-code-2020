import java.io.File

/**
 *
Right 1, down 1. = 79 trees
Right 3, down 1. = 156 trees
Right 5, down 1. = 85 trees
Right 7, down 1. = 82 trees
Right 1, down 2. = 41 trees

 79*156*85*82*41=
 */
fun main(args: Array<String>) {

    val input = getDec3Input()
    val columnLength = input[0].size

    println("we have ${input.size} items")

    var treesEncountered = 0
    val itemsAcross = 1

    var currentPosition = 0
    var row = 0

    while (row < input.size) {
        val item:Char = input[row][currentPosition]

        if (item == '#') {
            treesEncountered ++
        }

        currentPosition += itemsAcross

        // if we're over the max columns, let's loop back
        if (currentPosition >= columnLength) {
            currentPosition -= columnLength
        }

        row += 2
    }

    println("we encountered $treesEncountered trees")
}

fun getDec3Input() : List<CharArray> {
    val map = ArrayList<CharArray>()

    File("src/main/resources/dec3Input.txt")
        .forEachLine {
            map.add(it.toCharArray())
        }

    return map
}
