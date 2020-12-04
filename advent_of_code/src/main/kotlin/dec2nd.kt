import java.io.File

fun main(args: Array<String>) {
    var validPasswords = 0;

    File("src/main/resources/dec2Input.txt")
        .forEachLine {
            if (validatePassword_partTwo(it)) {
                validPasswords ++
            }
        }

    println("we got $validPasswords valid passwords" )
}

fun validatePassword_partOne(line: String) : Boolean {

    val components = line.split(" ")

    val frequencyItems = components[0].split("-")

    val fewest = Integer.parseInt(frequencyItems[0])
    val most = Integer.parseInt(frequencyItems[1])
    val letter = components[1].substring(0, 1)
    val password = components[2]
    val occurance = password.count{ letter.contains(it) }

    println("letter: $letter should occur at least $fewest and at most $most in $password - currently occurs $occurance")

    // password is valid
    if (occurance in fewest..most) {
        return true
    }

    return false
}

fun validatePassword_partTwo(line: String) : Boolean {

    val components = line.split(" ")

    val frequencyItems = components[0].split("-")

    val char1 = Integer.parseInt(frequencyItems[0]) -1
    val char2 = Integer.parseInt(frequencyItems[1]) -1
    val letter:Char = components[1].get(0)
    val password = components[2]

    if ( (password[char1] == letter)
            .xor( password[char2] == letter ) ) {
        return true
    }

    return false
}
