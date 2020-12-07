import java.io.File
import java.lang.Exception

fun main(args: Array<String>) {
    var highestSeatId = 0
    val seats = ArrayList<Int>()

    File("src/main/resources/dec5_input.txt")
        .forEachLine {
            val seatId = dec5th_boardingPassToSeatId(it)
            seats.add(seatId)
            if (seatId > highestSeatId) {
                highestSeatId = seatId
            }
        }

    seats.sort()
    var previousSeat = 0

    for (seat in seats) {

        if (previousSeat != 0
            && previousSeat != seat -1) {
            println("seatid is $seat but previousSeat is $previousSeat")
        }

        previousSeat = seat
    }

//    println("highest seat id = $highestSeatId")

}

fun dec5th_boardingPassToSeatId(input: String) : Int{

    // calculate row and column
    val row = dec5th_getRow(input.substring(0, input.length-3))
    val col = dec5th_getColumn(input.substring(input.length-3, input.length))

    // get seat id
    return dec5th_getSeatId(row, col)
}

fun dec5th_getRow(input: String) : Int {
    return convertStringToBinary(input, "B", "F")
}

fun dec5th_getColumn(input: String) : Int {
    return convertStringToBinary(input, "R", "L")
}

private fun convertStringToBinary(input: String, letterOne: String, letterZero: String) : Int {
    val binaryInput = input
        .replace(letterOne, "1")
        .replace(letterZero, "0")

    return try {
        Integer.parseInt(binaryInput, 2)
    } catch (e: Exception) {
        0
    }
}

fun dec5th_getSeatId(row: Int, column: Int) : Int {
    return (row *8) + column
}