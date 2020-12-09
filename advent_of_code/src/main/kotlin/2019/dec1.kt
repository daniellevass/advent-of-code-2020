import java.io.File
import kotlin.math.floor

fun main(args: Array<String>) {
    var count = 0
    File("src/main/resources/2019/dec1_input.txt")
        .forEachLine {
            val initialFuel = dec1_calculateFuel(it)
            val moreFuel = dec1_calculateFuelForFuel(initialFuel)

            count += (initialFuel + moreFuel)
        }

    println(count)
}

fun dec1_calculateFuel(input: String) : Int {
    try {
        return dec1_fuelMaths(Integer.parseInt(input))

    } catch(e : Exception) {
        return 0
    }
}

fun dec1_fuelMaths(input: Int) : Int {
    return (Math.floor(input.toDouble() / 3) - 2).toInt()
}

fun dec1_calculateFuelForFuel(input: Int) : Int {
    var currentRecord = input
    var newTotal = 0

    while (currentRecord > 0) {
        currentRecord = dec1_fuelMaths(currentRecord)
        if (currentRecord > 0) {
            newTotal += currentRecord
        }

    }

    return newTotal
}