import java.io.File
import java.util.regex.Pattern

/**
byr (Birth Year)
iyr (Issue Year)
eyr (Expiration Year)
hgt (Height)
hcl (Hair Color)
ecl (Eye Color)
pid (Passport ID)
cid (Country ID)
 */
fun main(args: Array<String>) {
    var validRecords = 0

    val currentRecord = HashMap<String, String>()
    File("src/main/resources/dec4Input.txt")
        .forEachLine {
            if (it.isEmpty) {
                // finish the record
                if (currentRecord.isNotEmpty()) {
                    if (dec4th_validateRecord(currentRecord)) {
                        validRecords ++
                    }
                    currentRecord.clear()
                }
            } else {
                val newComponents = it.split(" ")
                for (component in newComponents) {
                    val parts = component.split(":")
                    currentRecord[parts[0]] = parts[1]
                }
            }
        }


    println("we got $validRecords valid records")
}


fun dec4th_validateRecord(record: Map<String, String>) : Boolean {
    var isValid = true

    val requiredKeys = arrayListOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    for (key in requiredKeys) {

        if (! record.containsKey(key)) {
            isValid = false
        } else {

            val item = record[key]!!

            when(key) {
                "byr" -> if ( ! dec4th_validateNumber(item, 1920, 2002) ){
                    println("byr invalid: $item")
                    isValid = false
                }
                "iyr" -> if ( ! dec4th_validateNumber(item, 2010, 2020) ){
                    println("iyr invalid: $item")
                    isValid = false
                }
                "eyr" -> if ( ! dec4th_validateNumber(item, 2020, 2030) ){
                    println("eyr invalid: $item")
                    isValid = false
                }
                "hgt" -> if ( ! dec4th_validateHeight(item)){
                    println("hgt invalid: $item")
                    isValid = false
                }
                "hcl" -> if ( ! dec4th_validateHexColour(item)){
                    println("hcl invalid: $item")
                    isValid = false
                }
                "ecl" -> if ( ! dec4th_validateEyeColour(item)){
                    println("ecl invalid: $item")
                    isValid = false
                }
                "pid" -> if ( ! dec4th_validatePassportNumber(item)){
                    println("pid invalid: $item")
                    isValid = false
                }
            }

        }
    }

    if (isValid){
        println(record.toString())
    }

    return isValid
}

fun dec4th_validateNumber(item: String, earliest: Int, latest: Int) : Boolean {
    try {
        val number = item.toInt()
        if (number in earliest..latest) {
            return true
        }
    } catch (e: Exception) {
        //nop
    }

    return false
}

/**
 * a number followed by either cm or in:
If cm, the number must be at least 150 and at most 193.
If in, the number must be at least 59 and at most 76.
 */
fun dec4th_validateHeight(item: String) : Boolean {

    val unit = item.substring(item.length -2, item.length).toLowerCase()
    val measurement = item.substring(0, item.length-2)

    if (unit == "cm") {
        return dec4th_validateNumber(measurement, 150, 193)
    } else if (unit == "in") {
        return dec4th_validateNumber(measurement, 59, 76)
    } else {
        // was not cm or in
        return false
    }
}

//hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
fun dec4th_validateHexColour(item: String) : Boolean {
    val colorPattern: Pattern = Pattern.compile("#([0-9a-f]{6})")
    return colorPattern.matcher(item).matches()
}

//exactly one of: amb blu brn gry grn hzl oth.
fun dec4th_validateEyeColour(item: String) : Boolean {
    val validColours = arrayListOf("amb","blu", "brn", "gry", "grn", "hzl", "oth")
    return validColours.contains(item)
}

fun dec4th_validatePassportNumber(item: String) : Boolean {
    try {
        val number = item.toInt()
        return item.length == 9
    } catch (e: Exception) {
        //nop
    }
    return false
}