import java.io.File
import kotlin.math.floor

fun main(args: Array<String>) {
    var elements = ArrayList<Int>()
    File("src/main/resources/2019/dec2_input.txt")
        .forEachLine {
           val parts = it.split(",")
            for (p in parts) {
                try {
                    elements.add(Integer.parseInt(p))
                } catch (e: Exception) {

                }
            }
        }
    println("we have ${elements.size} elements")

    for (a in 0..99 step 1) {
        for (b in 0..99 step 1) {

            val updatedInstructions = ArrayList<Int>(elements)
            updatedInstructions[1] = a
            updatedInstructions[2] = b

            val score = executeCode(instructions = updatedInstructions, 0 )

            if (score == 19690720) {
                println("a = $a and b = $b")
            }

        }
    }

    elements[1] = 1
    elements[2] = 10


}

private fun executeCode(instructions: ArrayList<Int>, outputIndex: Int) : Int {
    var index = 0
    var completed = false
    while(!completed) {

        when(instructions[index]) {
            1 -> {
                // add items together
                val indexA = instructions[index + 1]
                val indexB = instructions[index + 2]
                val indexC = instructions[index + 3]

                instructions[indexC] = instructions[indexA] + instructions[indexB]
            }
            2 ->{
                // times items together
                val indexA = instructions[index + 1]
                val indexB = instructions[index + 2]
                val indexC = instructions[index + 3]

                instructions[indexC] = instructions[indexA] * instructions[indexB]
            }
            99 -> {completed = true}
        }

        index +=4

        if (index > instructions.size) {
            completed = true
        }
    }

    return instructions[outputIndex]
}

