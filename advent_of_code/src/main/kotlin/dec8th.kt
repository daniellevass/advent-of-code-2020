import java.io.File
import java.lang.Exception

fun main(args: Array<String>) {

    val instructions = ArrayList<Instruction>()

    File("src/main/resources/dec8_input.txt")
        .forEachLine {
            instructions.add(dec8_processLine(it))
        }

    println("we have ${instructions.size} instructions")

    var accumulator: Int? = null
    var indexToChange:Int? = 0
    var completed = false

    while (!completed) {

        // what index did we try to change last time, let's change a different one
        val lastTriedIndex = indexToChange
        indexToChange = dec8_findNextNopOrJmpIndex(instructions, lastTriedIndex!! +1)
        println("lastTriedIndex = $lastTriedIndex - newIndex = $indexToChange")

        // if we ran out of items to change let's stop
        if (indexToChange == null) {
            return
        }

        // edit the next item
        val editedInstructions = ArrayList<Instruction>(instructions)
        val arg = editedInstructions[indexToChange].argument
        if (editedInstructions[indexToChange].operation == OPERATION.NO_OPERATION) {
            editedInstructions[indexToChange] = Instruction(OPERATION.JUMP, arg)
        }else if (editedInstructions[indexToChange].operation == OPERATION.JUMP) {
            editedInstructions[indexToChange] = Instruction(OPERATION.NO_OPERATION, arg)
        }

        // run the new code
        accumulator = dec8_executeCode(editedInstructions)

        // if we got our answer let's stop
        if (accumulator != null) {
            completed = true
        }
    }

    println("accumulator = ${accumulator}")
}

fun dec8_executeCode(instructions: List<Instruction>) : Int? {

    var completed = false
    var malformed = false
    val visitedInstructionIndexes = ArrayList<Int>()
    var accumulator = 0
    var currentIndex = 0

    while(!completed) {

        // get the current instruction
        val instruction = instructions[currentIndex]
        visitedInstructionIndexes.add(currentIndex)

        // switch on the type
        when (instruction.operation) {
            OPERATION.NO_OPERATION -> currentIndex ++
            OPERATION.JUMP -> currentIndex += instruction.argument
            OPERATION.ACCUMULATOR -> {
                accumulator += instruction.argument
                currentIndex ++
            }
        }

        // if new index is one we've already gone to - something went wrong
        if (visitedInstructionIndexes.contains(currentIndex)) {
            completed = true
            malformed = true
        }

        if (currentIndex == instructions.size) {
            completed = true
        }
    }

    return if (malformed) {
        null
    } else {
        accumulator
    }
}

fun dec8_findNextNopOrJmpIndex(instructions: List<Instruction>, index: Int) : Int? {
    var instructionIndex:Int? = null
    var i = index
    while (i < instructions.size && instructionIndex == null) {
        val item = instructions[i]
        if (item.operation == OPERATION.NO_OPERATION
            || item.operation == OPERATION.JUMP) {
            instructionIndex = i
        }
        i++
    }
    return instructionIndex
}

fun dec8_findLastNopOrJmp(input: List<Instruction>) : Instruction? {
    val reversedList = ArrayList<Instruction>(input).reversed()
    var foundItem:Instruction? = null
    var index = 0
    var skippedFirst = false
    while (foundItem == null && index < reversedList.size) {
        val item = reversedList[index]
        if (item.operation == OPERATION.NO_OPERATION
            || item.operation == OPERATION.JUMP) {
                if (!skippedFirst) {
                    skippedFirst = true
                } else {
                    foundItem = item
                }

        }
            index ++
    }
    return foundItem
}


fun dec8_processLine(input: String) : Instruction {
    val parts = input.split(" ")

    var operation = OPERATION.NO_OPERATION
    if (parts[0] == "acc") {
        operation = OPERATION.ACCUMULATOR
    } else if (parts[0] == "jmp") {
        operation = OPERATION.JUMP
    }

    try {
        val argument = Integer.parseInt(parts[1])
        return Instruction(operation, argument)
    } catch (e: Exception) {
        return Instruction(operation, 0)
    }

}

enum class OPERATION {
    ACCUMULATOR, JUMP, NO_OPERATION
}

data class Instruction(val operation: OPERATION, val argument: Int)
