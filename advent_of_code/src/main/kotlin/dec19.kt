import java.io.File
import kotlin.math.acos

fun main(args: Array<String>) {

    val output = dec19_readFile("src/main/resources/dec19_sample.txt")

    val node0 = dec19_formatRules(output.first)
}

/**
0: 4 1 5
1: 2 3 | 3 2
2: 4 4 | 5 5
3: 4 5 | 5 4
4: "a"
5: "b"
 */

fun dec19_readFile(filename: String) : Pair<Map<Int, String>, List<String>> {
    var isRuleMode = true

    val rules = HashMap<Int, String>()
    val messages = ArrayList<String>()

    File(filename)
        .forEachLine {

            // break in the text
            if (it.length == 0) {
                isRuleMode = false
            } else {

                if (isRuleMode) {

                    val pair = dec19_parseRule(it)
                    rules[pair.first] = pair.second

                } else {
                    messages.add(it)
                }
            }
        }

    return Pair(rules, messages)
}

fun dec19_parseRule(line: String): Pair<Int, String> {
    // 0: 4 1 5
    val parts = line.split(": ")
    val key = parts[0].toInt()

    return Pair(key, parts[1])
}

data class RuleNode(
    val ruleNumber: Int,
    val childrenA: ArrayList<RuleNode> = ArrayList(),
    val childrenB: ArrayList<RuleNode> = ArrayList(),
    val symbol : String? = null
)

fun dec19_formatRules(initialMap : Map<Int, String>) : RuleNode {

    val nodeMap = HashMap<Int, RuleNode>()

    // create all our potential nodes
    initialMap.forEach { (key, value) ->
        if (value.contains('"')) {
            // is a simple character
            val char = value.subSequence(1, 2).toString()
            nodeMap[key] = RuleNode(ruleNumber = key, symbol = char)
        } else {
            nodeMap[key] = RuleNode(ruleNumber = key)
        }
    }

    // fill in the children
    initialMap.forEach{(key, value) ->
        if (!value.contains('"')) {

            val node = nodeMap[key]!!

            // value = 2 3 | 3 2
            val parts = value.split(" | ")
            val partA = parts[0].split(" ")

            for (a in partA) {
                val childKey: Int = a.toInt()
                node.childrenA.add(nodeMap[childKey]!!)
            }

            // not all rules have a B part
            if (parts.size > 1) {
                val partB = parts[1].split(" ")
                for (b in partB) {
                    val childKey: Int = b.toInt()
                    node.childrenB.add(nodeMap[childKey]!!)
                }
            }

        }
    }

    println("node 0 has ${nodeMap[0]!!.childrenA.size} children A and ${nodeMap[0]!!.childrenB.size} children B")
    return nodeMap[0]!!
}

fun dec19_createFullRuleSet(node0: RuleNode) : List<String> {
    return dec19_visitNode(node0).first
}

fun dec19_visitNode(node: RuleNode) : Pair<List<String>, List<String>> {

    val aChildren = ArrayList<String>()
    val bChildren = ArrayList<String>()

    /*
0: 4 1 5
1: 2 3 | 3 2
2: 4 4 | 5 5
3: 4 5 | 5 4
4: "a"
5: "b"

4 = a
1 = 2,3 = 4,4 4,5 = aa ab
5 = a

= a aa ab a
     */

    if (node.childrenA.size > 0) {

        var aRule = ""
        for (a in node.childrenA) {
            val newInfo = dec19_visitNode(a)
            for (item in newInfo.first) {
                aRule += item
            }
        }
    }

    // we got to the end
    if (node.symbol != null) {
        aChildren.add(node.symbol)
    }

   return Pair(aChildren, bChildren)
}

