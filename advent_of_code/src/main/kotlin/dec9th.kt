import java.io.File
import java.lang.Exception

fun main(args: Array<String>) {

    val input = ArrayList<Int>()

    // get the input
    val preambleLength = 25
    File("src/main/resources/dec9_input.txt")
        .forEachLine {
            try {
                input.add(Integer.parseInt(it))
            } catch(e : Exception) {
                //nop
            }
        }
    
    // loop through the array and find the first item that doesn't add up
    var valueThatDoesntAddUp = 0
    var indexOfValue = 0
    for (i in (preambleLength +1)..input.size step 1) {

        if (!dec9_isLastNumberAnAdditionOfPreviousNumbers(input.subList(i-(preambleLength+1), i))) {
            println("${input[i-1]} cannot be found")
            valueThatDoesntAddUp = input[i-1]
            indexOfValue = i-1
        }
    }

    // loop through and find longest string that add up
    val inputBeforeBroken = ArrayList<Int>(input.subList(0, indexOfValue))
    var continuousItems = ArrayList<Int>()
    val tempItems = ArrayList<Int>()

    for (a in 0 until inputBeforeBroken.size step 1) {
        var total = inputBeforeBroken[a]
        for (b in (a+1) until inputBeforeBroken.size step 1) {
             total += inputBeforeBroken[b]

            if (total == valueThatDoesntAddUp) {
                val items = inputBeforeBroken.subList(a, (b +1))
                if (items.size > continuousItems.size) {
                    continuousItems = ArrayList(items)
                }
            }

        }
    }

    println(continuousItems.toString())
    continuousItems.sort()
    val addition = continuousItems[0] + continuousItems[continuousItems.size -1]
    println("smallest = ${continuousItems[0]} and biggest = ${continuousItems[continuousItems.size -1]} = addition ${addition}")

}

fun dec9_isLastNumberAnAdditionOfPreviousNumbers(input: List<Int>) : Boolean {
//    println("Finding ${input[input.size-1]} = we have ${input.size} things")
    var found = false
    val toFind = input[input.size-1]
    val numbers = input.subList(0, input.size -1) // cut off the last element

    for (a in numbers) {
        for (b in numbers) {
            if (a != b && (a+b) == toFind) {
                found = true
            }
        }
    }
    return found
}
