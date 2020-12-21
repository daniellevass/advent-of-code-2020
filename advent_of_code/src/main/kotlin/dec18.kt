import java.io.File

fun main(args: Array<String>) {

    var sum = 0L
    File("src/main/resources/dec18_input.txt")
        .forEachLine {
            sum += dec18_readLine(it)
        }

    println("the total is $sum")
    //not 219_107_660 or 4_514_074_956
}

fun dec18_readLine(line: String) : Long {
    //e.g. 1 + (2 * 3) + (4 * (5 + 6))
    val calculator = Calculator()

    // scan through until you get to a bracket,
    // find the end bracket and send that through the readline machine
    var i = 0
    while(i < line.length) {

        val char = line[i]

        if (char == '(') {
            // find the end of the bracket
            // add the length of that to i
            val endBracket = dec18_findMatchingEndBracketIndex(line, i)
            val bracketTotal = dec18_readLine(line.substring(i+1, endBracket))
            calculator.processInstruction(bracketTotal)
//            println("character at $i is a starting bracket = $char end bracket is $endBracket")
//            println("substring should be ${line.substring(i+1, endBracket)}")
            i = endBracket

        } else if (char == ' ') {
            // ignore -> skip
        } else if (char == '+') {
            calculator.configureMode(CalculatorMode.ADDITION)
        } else if (char == '*') {
            calculator.configureMode(CalculatorMode.MULTIPLICATION)
        } else {
            // it's a number -> process the number
            calculator.processInstruction(Integer.parseInt(char.toString()).toLong())
        }

        i++
    }

//    println("calculating '$line' to equal ${calculator.total}")

    return calculator.total
}

fun dec18_part2(line: String) : Long {
    val input = dec18_readLinePart2(line)

    return dec18_readLine(input)
}

fun dec18_readLinePart2(line: String) : String {

    // create a string of just multiplications
    // e.g. 1 + 2 * 3 + 4 * 5 + 6 ->  (1*2) * (3+4) * (5+6)
    val input = line.replace(" ", "")
    var newLine = ""
    var i = 0
    while (i < input.length) {
        //is the next char a +
        if ( (i +1) < input.length
            && input[i+1] == '+') {

            // is the third char a bracket - if so, we need to chunk to the end of the bracket
            if ( (i+2)< input.length && input[i+2] == '(') {

                val endBracketIndex = dec18_findMatchingEndBracketIndex(input, i+2)
                val chunkedLine = input.substring(i+2, endBracketIndex +1)
                val processLine = dec18_readLinePart2(chunkedLine)
                newLine += "(${input[i]}+${processLine})"
                i = endBracketIndex +1
            } else {
                newLine += "(${input[i]}+${input[i+2]})"
                i+=3
            }

        } else {
            newLine += input[i]
            i++
        }
    }

    println("new line = $newLine")

    return newLine
}

fun dec18_findMatchingEndBracketIndex(line: String, startingBracketIndex: Int) : Int {
    var i = startingBracketIndex +1
    var newBrackets = 0

    while (i < line.length) {
        val char = line[i]

        if (char == '(') {
            newBrackets ++
        } else if (char == ')') {

            if (newBrackets == 0) {
                return i
            } else {
                newBrackets --
            }

        }
        i++
    }
    return i
}

enum class CalculatorMode { ADDITION, MULTIPLICATION}
class Calculator () {
    lateinit var mode: CalculatorMode
    var total: Long = 0

    fun processInstruction(number: Long) {
        if (this::mode.isInitialized) {
            if (this.mode == CalculatorMode.ADDITION) {
                total += number
            } else if (this.mode == CalculatorMode.MULTIPLICATION) {
                total *= number
            }
        } else {
            total = number.toLong()
        }
    }

    fun configureMode(mode: CalculatorMode) {
        this.mode = mode
    }

}