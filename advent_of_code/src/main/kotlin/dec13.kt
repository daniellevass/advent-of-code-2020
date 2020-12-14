import java.io.File
import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.floor

fun main(args: Array<String>) {

    val file = ArrayList<String>()
    File("src/main/resources/dec13_input.txt")
        .forEachLine {
            file.add(it)
        }

    // get the arrival time
    val myArrivalTime = Integer.parseInt(file[0])

    // get the other bus times
    val busTimes = dec13_part2_readBusTimes(file[1])

//    dec13_part2_doHardMaths(busTimes)

    dec13_part2_stepComplicately(busTimes)
}

fun dec13_part1(myArrival : Int, busTimes: List<Int>) {

    var lowestWaitTime = dec13_getTimeNearestNextDeparture(busTimes[0], myArrival)
    var bestBus = busTimes[0]

    for (i in 1 until busTimes.size) {

        val time = dec13_getTimeNearestNextDeparture(busTimes[i], myArrival)

        if (time < lowestWaitTime) {
            lowestWaitTime = time
            bestBus = busTimes[i]
        }

    }

    println("best bus is ${bestBus} where you wait ${lowestWaitTime} resulting in ${bestBus * lowestWaitTime}")

}

fun dec13_getTimeNearestNextDeparture(bus: Int, arrival: Int) : Int {
    // if i arrive at 10 and the bus goes every 6,
    var wholeNumber: Double = 0.0
    if (arrival > bus) {
        wholeNumber = floor(((arrival / bus).toDouble()))
    }

    wholeNumber ++

    return abs((arrival - (wholeNumber * bus)).toInt())
}

fun dec13_part1_readBusTimes(line: String) : List<Int> {
    val busParts = line.split(",")
    val busTimes = ArrayList<Int>()

    for (part in busParts) {
        if (part != "x") {
            busTimes.add(Integer.parseInt(part))
        }
    }

    return busTimes
}

data class Bus(val interval: Int, val index: Int)
fun dec13_part2_readBusTimes(line: String) : List<Bus> {
    val busParts = line.split(",")
    val busTimes = ArrayList<Bus>()

    for (i in busParts.indices step 1) {
        val part = busParts[i]
        if (part != "x") {
            busTimes.add(Bus(interval = Integer.parseInt(part), index = i))
        }
    }

    return busTimes
}

fun dec13_part2_doHardMaths(busTimes: List<Bus>) : Long {

    // we only need to go in multiplications of the bus times
    // e.g. 17,x,13,19 is 3417
    // -> bus 17 = 201
    // -> bus x gets a free ride
    // -> bus 13 = 263
    // -> bus19 = 180

    // sort by interval
    val sortedBusTimes = busTimes.sortedByDescending { it.interval }

    println("our biggest bus time is ${sortedBusTimes[0]}")

    val earliest:Long = 62_932_475_746_153
    val latest = earliest + 1_000_000_000L
    var time0th = 0L

    for (a in earliest..latest) {

        val bigBusTime = (sortedBusTimes[0].interval * a)
        time0th = bigBusTime - sortedBusTimes[0].index

        if (dec13_part2_validateAllBussesForTime(time0th, sortedBusTimes)) {
            break
        }
    }
    return time0th
}

fun dec13_part2_doesBusDepartFromTimeWithIndex(time: Long, bus: Bus) : Boolean{
    return (time + bus.index) % bus.interval == 0L
}

fun dec13_part2_validateAllBussesForTime(time: Long, busList: List<Bus>) : Boolean {
    var allValid = true
    for (bus in busList) {
        if (!dec13_part2_doesBusDepartFromTimeWithIndex(time, bus)) {
            allValid = false
        }
    }

    return if (allValid ) {
        println("we got $time with the first valid")
        true
    } else {
        false
    }
}

fun dec13_part2_stepComplicately(buses: List<Bus>) {

    var timestamp: Long = 0L
    var step:Long = 1L

    for (bus in buses) {

        while ( (timestamp + bus.index) % bus.interval != 0L) {
            timestamp += step
        }

        // because they're co-prime numbers we don't need to find
        // the lowest common denominator, we can just times by the interval
        step *= bus.interval
    }

    println("last timestamp = $timestamp")
}
