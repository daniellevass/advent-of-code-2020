import java.io.File
import java.math.BigInteger

fun main(args: Array<String>) {
    dec14_part2()
}

//fun dec14_part1() {
//    var currentMask = ""
//    val map = HashMap<Int, BigInteger>()
//
//    val file = ArrayList<String>()
//    File("src/main/resources/dec14_input.txt")
//        .forEachLine {
//
//            if (it.startsWith("mask")){
//                currentMask = dec14_readMask(it)
//            } else if (it.startsWith("mem")) {
//                val pair = dec14_readMem(it)
//                val binaryStringWithMaskApplied = dec14_applyMaskToBinaryString(pair.second, currentMask)
//                map[pair.first] = binaryStringWithMaskApplied
//            }
//        }
//
//    var sum:BigInteger = BigInteger("0")
//    map.forEach { (_, value) ->
//        sum += value
//    }
//
//    println("we got ${map.size} things which add up to $sum" )
//}

fun dec14_part2() {
    var currentMask = ""
    val map = HashMap<BigInteger, BigInteger>()
    File("src/main/resources/dec14_input.txt")
        .forEachLine {

            if (it.startsWith("mask")){
                currentMask = dec14_readMask(it)
                println("updated mask to $currentMask")
            } else if (it.startsWith("mem")) {
                val pair = dec14_readMem(it)
                // first is the memory address -> second is the value
                val memoryAddresses = dec14_part2_findAllAddressesForBinaryString(pair.first, currentMask)
                for (address in memoryAddresses) {
                    map[address] = pair.second
                }

                println("applying mask to ${pair.first.toBigInteger(2)} and got ${memoryAddresses.size} addresses to add ${pair.second}")
            }
        }

    var sum:BigInteger = BigInteger("0")
    map.forEach { (_, value) ->
        sum += value
    }

    println("we got ${map.size} things which add up to $sum" )
}

fun dec14_readMask(line: String) : String {
    val parts = line.split(" = ")
    return parts[1]
}

fun dec14_readMem(line: String) : Pair<String, BigInteger> {
    val parts = line.split(" = ")

    val value = parts[1].toBigInteger()
    val key = parts[0].substring(4, parts[0].length -1).toBigInteger()

    //Integer.parseInt(key)
    return Pair(dec14_intTo32BitBinaryString(key), value)
}

fun dec14_intTo32BitBinaryString(number: BigInteger) : String{
    return String.format("%36s", (number).toString(2)).replace(' ', '0')
}

fun dec14_applyMaskToBinaryString(binaryString: String, mask: String) : BigInteger {

    var newBinaryString = binaryString

    for(m in mask.indices) {
        val char = mask[m]

        if (char == '0') {
           newBinaryString = newBinaryString.replaceRange(m, m + 1, "0")
        } else if (char == '1') {
            newBinaryString = newBinaryString.replaceRange(m, m + 1, "1")
        }

    }

    return newBinaryString.toBigInteger(2)
}

fun dec14_part2_findAllAddressesForBinaryString(binaryString: String, mask: String): List<BigInteger> {
    var tempAddresses = ArrayList<String>()

    // add the first item
    if (mask[0] == 'X') {
        tempAddresses.add("0")
        tempAddresses.add("1")
    } else {

        if (mask[0] == '1') {
            tempAddresses.add("1")
        } else {
            tempAddresses.add(binaryString.substring(0, 1))
        }
    }

    // read through each char and add to the array
    for(i in 1 until mask.length) {

        val updatedAddresses = ArrayList<String>()

        if (mask[i] == 'X') {
            // special char!

            // for each item in the array, we have to add two variants!
            for (address in tempAddresses) {
                updatedAddresses.add(address + "0")
                updatedAddresses.add(address + "1")
            }

        } else {
            // not a special character, we can just add it directly
            var charToAdd = binaryString.substring(i, i+1)
            if (mask[i] == '1') charToAdd = "1"

            for (address in tempAddresses) {
                updatedAddresses.add(address + charToAdd)
            }
        }

        // overwrite the addresses with our new addresses
        tempAddresses = ArrayList(updatedAddresses)
    }

    val addresses = ArrayList<BigInteger>()
    for (t in tempAddresses) {
        addresses.add(t.toBigInteger(2))
    }
    return addresses
}

