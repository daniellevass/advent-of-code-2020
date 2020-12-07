import java.io.File

fun main(args: Array<String>) {

    var currentRecord = ArrayList<List<Char>>()
    var count = 0

    File("src/main/resources/dec6_input.txt")
        .forEachLine {
            if (it.isEmpty) {
                count += dec6th_calculateAllEntries(currentRecord)
                currentRecord.clear()
            } else {
                currentRecord.add(it.toList())
            }
        }

    // add the last record
    count += dec6th_calculateAllEntries(currentRecord)

    println("total count = $count")
}

fun dec6th_calculateUniqueEntries(input: List<Char>) : Int {
    return input.distinct().size
}

fun dec6th_calculateAllEntries(input: List<List<Char>>) : Int{
    var entries = 0

    var firstPerson = input[0]
    var otherPeople = input.subList(1, input.size)

    // loop over the first persons answers, see if they're in all the others
    for (char in firstPerson) {

        var found = true
        for (person in otherPeople) {
            if (! person.contains(char)) {
                found = false
            }
        }

        if (found) {
            entries ++
        }

    }

    return entries
}


