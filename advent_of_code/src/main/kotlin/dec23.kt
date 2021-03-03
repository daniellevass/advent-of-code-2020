import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main(args: Array<String>) {

    var cups = dec23_readInput("156794823")

    for (i in 1..100) {
        println("round $i")
        val newCups = dec23_processRound(cups)
        cups = newCups
    }

    val conclusion = dec23_concludeRound(cups)
    println(conclusion)


}

fun dec23_readInput(input: String) : ArrayList<Int> {

    val items = ArrayList<Int>()

    val parts = input.split("").filter { it.isNotEmpty() }
    for (p in parts) {
        items.add(p.toInt())
    }

    return items
}

fun dec23_processRound(input: ArrayList<Int>) : ArrayList<Int> {

    val newCups = ArrayList<Int>()

    val currentCup = input[0]
    input.remove(currentCup)

    // get the next 3 cups
    val heldCups = ArrayList<Int>()
    heldCups.addAll( input.subList(0, 3))

    // remove the held cups from the input
    input.removeAll(heldCups)

    println("start finding destination cup = " + System.nanoTime())
    // find destination cup
    var found = false
    var destinationCup = 0
    var cupToSearch = currentCup -1
    while (! found) {

        // does our current input contain the cup
        if (input.contains(cupToSearch)) {
            destinationCup = cupToSearch
            found = true
        }

        // did not find that cup
        cupToSearch --

        // if we got to 0, we're out of cups, loop back around to the largest cup we have
        if (cupToSearch <= 0) {
            cupToSearch = Collections.max(input)
        }
    }
    println("end finding destination cup = " + System.nanoTime())

//    println("current cup = $currentCup - held cups = $heldCups - destination cup is $destinationCup")

    // get index of destination cup
    val destinationCupIndex = input.indexOf(destinationCup)

    // put all the cups up to and including the destination cup
    newCups.addAll(input.subList(0, destinationCupIndex +1))
    // add the held cups
    newCups.addAll(heldCups)
    // add the rest of the inputs
    newCups.addAll(input.subList(destinationCupIndex +1, input.size))
    // finally add the last cup
    newCups.add(currentCup)

//    println("$newCups")
    println("end = " + System.nanoTime())
    return newCups
}

fun dec23_processRound_part2(input: List<Int>) : List<Int> {
//    println("start = " + System.nanoTime())
    val newCups = ArrayList<Int>(1000000)
    val currentCup = input[0]
    // get the next 3 cups
    val heldCups = ArrayList<Int>(3)
    heldCups.addAll( input.subList(1, 4))

    val remainingCups = ArrayList<Int>(1000000)
    remainingCups.addAll(input.subList(4, input.size))

//    println("start finding destination cup = " + System.nanoTime())
    // find destination cup
    var found = false
    var destinationCup = 0
    var cupToSearch = currentCup
    while (! found) {

        // subtract one from the current cup
        cupToSearch --

        // if we got to 0, we're out of cups, loop back around to the largest cup we have
        if (cupToSearch <= 0) {
            cupToSearch = Collections.max(remainingCups)
        }

        // does our current input contain the cup
        if (remainingCups.contains(cupToSearch)) {
            destinationCup = cupToSearch
            found = true
        }

    }

//    println("end finding destination cup = " + System.nanoTime())

//    println("current cup = $currentCup - held cups = $heldCups - destination cup is $destinationCup")

    // get index of destination cup
    val destinationCupIndex = remainingCups.indexOf(destinationCup)

    // put all the cups up to and including the destination cup
    newCups.addAll(remainingCups.subList(0, destinationCupIndex +1))
    // add the held cups
    newCups.addAll(heldCups)
    // add the rest of the inputs
    newCups.addAll(remainingCups.subList(destinationCupIndex +1, remainingCups.size))
    // finally add the last cup
    newCups.add(currentCup)

//    println("$newCups")
//    println("end = " + System.nanoTime())
    return newCups
}

fun dec23_concludeRound(input: List<Int>) : String {
    var result = ""
    // find the index of 1
    val indexOf1 = input.indexOf(1)

    // loop over the bits after
    for (x in input.subList(indexOf1 +1, input.size)) {
        result += x.toString()
    }

    for (y in input.subList(0, indexOf1)) {
        result += y.toString()
    }

    return result
}

fun dec23_part2_conclude(input: List<Int>) {
    // find the index of 1
    val indexOf1 = input.indexOf(1)

    println("cups immediately to the right of 1 are:")
    println("${input[indexOf1+1]}")
    println("${input[indexOf1+2]}")

}

fun dec23_part2_makeInput(input: List<Int>) : ArrayList<Int> {

    var moreCups = ArrayList<Int>()

    // next item
    var nextBiggest = Collections.max(input) +1

    while (moreCups.size < 1_000_000) {
        moreCups.add(nextBiggest)
        nextBiggest ++
    }
    return moreCups
}



//// try with maps instead of lists - maybe it's faster?
fun dec23_map_readInput(input: String , makeAMillion: Boolean) : Map<Int, Int> {

    val initialCups = dec23_readInput(input)
    val map = HashMap<Int, Int>()

    for ((index, cup) in initialCups.withIndex()) {
        map[index] = cup
    }

    if (makeAMillion) {
        var nextBiggest = Collections.max(initialCups) +1
        var index = map.size
        while (map.size <1_000_000) {
            map[index] = nextBiggest
            nextBiggest ++
            index ++
        }
    }

    return map
}

fun dec23_map_processRound(cups: Map<Int, Int>) : Map<Int, Int> {

    val currentCup = cups[0]!!
    val heldCups = listOf(cups[1]!!, cups[2]!!, cups[3]!!)

    val remainingCups = cups.filterKeys { it > 3 }

    var found = false
    var destinationCup = 0
    var cupToSearch = currentCup
    while (!found) {
        // subtract one from the current cup
        cupToSearch --

        // if we got to 0, we're out of cups, loop back around to the largest cup we have
        if (cupToSearch <= 0) {
            cupToSearch = Collections.max(remainingCups.values)
        }

        // does our remaining values contain the cup that we're searching for
        if (remainingCups.containsValue(cupToSearch)) {
            destinationCup = cupToSearch
            found = true
        }

    }

    // create the output map
    val outputMap = HashMap<Int, Int>()
    var i = 0
    remainingCups.forEach { (_, value) ->

        if (value == destinationCup) {
            // put our destination cup
            outputMap[i] = value
            i++

            // put our held cups
            for (heldcup in heldCups) {
                outputMap[i] = heldcup
                i++
            }

        } else {
            outputMap[i] = value
            i++
        }

    }

    // put our current cup right at the end
    outputMap[i] = currentCup

    return outputMap
}