import java.io.File

fun main(args: Array<String>) {

    val input = ArrayList<Int>()

    input.add(0)
    // get the input
    File("src/main/resources/dec10_input.txt")
        .forEachLine {
            try {
                input.add(Integer.parseInt(it))
            } catch(e : Exception) {
                //nop
            }
        }

    //sort the list
    input.sort()

    var previousNumber = input[0]
    val tempArray = ArrayList<Int>()
    var count = 1L // it overflowed an int
    for (i in 1 until input.size step 1) {
        val number = input[i]
        if (previousNumber == (number -1)) {

            tempArray.add(number)
        } else {

            when (tempArray.size) {
                4 -> count *=7
                3 -> count *=4
                2 -> count *=2
                else -> {
                    println("we had another number ${tempArray.size}")
                }
            }

            tempArray.clear()
        }

        previousNumber = number
    }

    // do the final one
    when (tempArray.size) {
        4 -> count *=7
        3 -> count *=4
        2 -> count *=2
    }

    println(count)

}

fun dec_part1(input: List<Int>) {
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
            i - previousNumber ==2 -> {
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
