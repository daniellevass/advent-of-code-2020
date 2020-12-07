import java.io.File

/*
1*(5+5*(1*(3*(5*(2+1+3*(2+2+1+1)+3)+5+5*(5)+5+2+5)+3+1*(4*(3*(1)+3)+4+3*(2+2+1+1)+3+3+5)+1+3*(3*(1)+3)+3)+1)+5+4*(4*(4*(3*(1)+3)+4+3*(2+2+1+1)+3+3+5)+4)+4+1*(1+1*(4*(1)+4)+1+3+2*(1)+2)+1)+1

 */
fun main(args: Array<String>) {

    // create bag map
    val bagMap = HashMap<String, List<BagContainer>>()
    File("src/main/resources/dec7_input.txt")
        .forEachLine {

            val words = it.split(" ")
            val source = "${words[0]}-${words[1]}"
            bagMap[source] = dec7_convertLineToMap(words)
        }

    // loop through each item, can it contain this bag
//    findBagsThatContain("shiny-gold", bagMap)
    calculateBagTotal("shiny-gold", bagMap)
}

private fun findBagsThatContain(bagToFind: String, bagMap: Map<String, List<BagContainer>>) {
    val bagsThatCanContainBagToFind = arrayListOf(bagToFind)
    var found = false

    while (!found) {

        val countBefore = bagsThatCanContainBagToFind.size

        for (bag in bagMap) {
            for (subBag in bag.value) {
                if (bagsThatCanContainBagToFind.contains(subBag.name)) {

                    // don't add it if it already exists in the map
                    if (!bagsThatCanContainBagToFind.contains(bag.key)) {
                        bagsThatCanContainBagToFind.add(bag.key)
                    }
                }
            }
        }
        // we didn't add any new bags
        if (countBefore == bagsThatCanContainBagToFind.size) {
            found = true
        }
    }

    println("we have ${bagsThatCanContainBagToFind.size} bags")
    println(bagsThatCanContainBagToFind.toString())
}

private fun calculateBagTotal(bagToFind: String, bagMap: Map<String, List<BagContainer>>) {

    var completed = false
    var number = 0
    var previousNumber = 0

    // let's create a tree for shiny-bags
    var shinyBag = bagMap[bagToFind]!!
    var tree = createNode(BagContainer("shiny-gold", 1), bagMap)

    preorderTraversal(tree)


}

fun preorderTraversal(node: Node) : Int {

    var count = performAction(node.bag)
    var message = ""
    if (node.items != null) {
        print("*(")
        for (child in node.items!!) {
            message += preorderTraversal(child)
            if (node.items!!.indexOf(child) != node.items!!.size -1) {
                print("+")
            }

        }
        print(")+" + node.bag.number )
    }

//    println(message)
    return count
}

fun performAction(bagContainer: BagContainer) : Int {
    print("${bagContainer.number}")
    return bagContainer.number
}

fun createNode(bagContainer: BagContainer, bagMap: Map<String, List<BagContainer>>) : Node {
    var node = Node(bag = bagContainer)
    var childBags = bagMap[bagContainer.name]!!
//    println("creating node for ${bagContainer.name}")
    for (child in childBags) {

        if (node.items == null) {
            node.items = ArrayList<Node>()
        }
        node.items!!.add( createNode(child, bagMap) )
    }
    return node
}

class Node(
    var bag: BagContainer,
    var items: ArrayList<Node>? = null
) {
    override fun toString(): String {
        return "Node(bag=$bag, items=$items)"
    }
}

data class BagContainer(val name: String, val number: Int)

fun dec7_convertLineToMap(words: List<String>) : List<BagContainer> {
    val bags = ArrayList<BagContainer>()

    if (words.contains("no") && words.contains("other")) {
        return bags
    }

    val filteredWords = words.subList(4, words.size)

    for (i in 0 until filteredWords.size step 4) {
        val bagContainer = BagContainer(name = "${filteredWords[i +1]}-${filteredWords[i+2]}",
            number = Integer.parseInt(filteredWords[i]))
        bags.add(bagContainer)
    }

    return bags
}
