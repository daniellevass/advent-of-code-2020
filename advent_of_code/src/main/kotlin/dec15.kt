fun main(args: Array<String>) {


}

fun dec15_runGame(input: List<Int>, gameLength: Int) : Int {
    val map = HashMap<Int, Int>() // number, last time we saw it

    // all indexes start at 0
    var lastSpoken = input.first()

    // create the starting points
    for (i in 1 until input.size) {
        // set the map[lastSpoken] item to the last index we saw it, which is 1
        map[lastSpoken] = i
        // update the next last spoken item to the next thing
        lastSpoken = input[i]
    }

    // start with the next turn e.g. if we had 3 preloaded answers, this should be the 4th,
    // play until we say to stop
    for (i in (input.size +1)..gameLength step 1) {

        if (map.containsKey(lastSpoken)) {
            // we have seen this item before

            val lastSpokenIndex:Int = map[lastSpoken]!! // when did we last see it
            val turnsBetween = ( (i-1) - lastSpokenIndex) // when did we see it, it's not this turn, but the previous turn

//            println("turn ${i} lastspoken = $lastSpoken seen before, at ${lastSpokenIndex}  which is $turnsBetween turns between")

            // we're going to update our previous
            map[lastSpoken] = i-1
            lastSpoken = turnsBetween
        } else {
            // this item is new! we've not seen it before, set the map item to be 0
//            println("turn ${i} lastspoken= $lastSpoken item (new)")
            map[lastSpoken] = i -1
            lastSpoken = 0
        }

    }


    return lastSpoken
}

fun dec15_calculateNextNumberBetter(lastNumber: Int, map: Map<Int, Int>) {

}

//fun dec15_calculateNextNumber(numbers: List<Int>) : Int {
//
//    val lastSpoken = numbers.last()
//
//    // when did we last see the number
//    var found = false
//    var i = numbers.lastIndex -1
//    var lastSpokenIndex = -1
//    while (!found && i >=0) {
//
//        if (lastSpoken == numbers[i]) {
//            found = true
//            lastSpokenIndex = (i)
//        }
//
//        i--
//    }
//
//    return if (found) {
//        numbers.lastIndex - lastSpokenIndex
//    } else {
//        0
//    }
//}
