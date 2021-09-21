import java.io.File

fun main(args: Array<String>) {

    val input = dec10_readFile("src/main/resources/dec10_input_take2.txt")

    dec10_part1(input)
}


fun dec10_readFile(fileName: String) : List<Int> {

    val input = ArrayList<Int>()

    input.add(0)

    File(fileName)
        .forEachLine {
            try {
                input.add(Integer.parseInt(it))
            } catch(e : Exception) {
                //nop
            }
        }

    //sort the list
    input.sort()

    return input
}

fun dec10_part1(input: List<Int>) {

    // loop through each item in the array, add to our counters
    var dif1 = 0
    var dif2 = 0
    var dif3 = 1 // because our device is always +3

    var previousNumber = 0 // starting with the wall

    for (i in input) {
        when {
            i - previousNumber == 1 -> {
                dif1++
            }
            i - previousNumber == 2 -> {
                dif2++
            }
            i - previousNumber == 3 -> {
                dif3++
            }
        }

        previousNumber = i
    }

    println("we got dif1 = $dif1 - dif2=$dif2 - dif3=$dif3")

}
