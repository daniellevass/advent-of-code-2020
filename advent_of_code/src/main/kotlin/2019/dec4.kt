package `2019`

import java.lang.Exception

fun main(args: Array<String>) {

    var invalidNumbers = 0
    for (i in 231832..767346) {
        if (!dec9_validateNumber(i)) {
            invalidNumbers ++
        }
    }

    println("we got $invalidNumbers invalid numbers")
}

fun dec9_validateNumber(number: Int) : Boolean {
    val numbers = number.toString().split("")

    return (dec9_validateDigitsIncrease(numbers)
            && dec9_validateSameDigits(numbers))
}

fun dec9_validateDigitsIncrease(numbers: List<String>) : Boolean {
    var valid = true
    var previousNumber: Int? = null

    for (n in numbers) {
        try {
            val number = Integer.parseInt(n)

            if (previousNumber != null) {
                if (number < previousNumber) {
                    valid = false
                }
            }


            previousNumber = number
        } catch (e: Exception) {
            valid = false
        }
    }

    return valid
}

fun dec9_validateSameDigits(numbers: List<String>) : Boolean {
    return numbers.distinct().size != 6
}
