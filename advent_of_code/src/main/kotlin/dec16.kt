import java.io.File

fun main(args: Array<String>) {

    val rules = ArrayList<TicketRule>()
    val tickets = ArrayList<List<Int>>()
    val myTicket = ArrayList<Int>()

    var nextRowMyTicket = false
    var nextRowNearbyTicket = false

    File("src/main/resources/dec16_input.txt")
        .forEachLine {

            if (nextRowMyTicket) {
                myTicket.addAll(dec16_readTicket(it))
                nextRowMyTicket = false
            }

            if (nextRowNearbyTicket) {
                tickets.add(dec16_readTicket(it))
            }

            if (it.contains("or")) {
                // it's a rule
                rules.add(dec16_readRule(it))
            }


            if (it == "your ticket:") {
                nextRowMyTicket = true
            }

            if (it =="nearby tickets:") {
                nextRowNearbyTicket = true
            }
        }

    println("we have ${rules.size} rules and ${tickets.size} tickets")

    val validTickets = ArrayList<List<Int>>()

    for (t in tickets) {
        if (dec16_returnInvalidItemsForTicket(t, rules).isEmpty()) {
            validTickets.add(t)
        }
    }

    println("we have ${validTickets.size} valid tickets")
    // work through each rule, find the positions that it could be
    for (rule in rules) {
        rule.potentialIndexes.addAll(dec16_forRuleFindIndexInTicket(rule, validTickets))
    }

    // some things have multiple potential indexes, now we need to work out how to remove duplicates

    var completed = false
    while(!completed) {
        // find the ones that can only satisfy 1 constraints
        for (rule in rules) {
            // set it to the final index
            var indexToRemove:Int? = null
            if (rule.potentialIndexes.size == 1) {
                rule.index = rule.potentialIndexes.first()
                indexToRemove = rule.potentialIndexes.first()
            }

            //remove from all other potentials
            if (indexToRemove != null) {
                for (r in rules) {
                    r.potentialIndexes.remove(indexToRemove)
                }
            }
        }

        // do all of the rules now have indexes?
        var allHaveIndexes = true
        for (r in rules) {
            if (r.index == null) {
                allHaveIndexes = false
            }
        }

        if (allHaveIndexes) {
            completed = true
        }
    }

    // all tickets should now have an index
    val departureItems = ArrayList<Int>()
    for (rule in rules) {
        if (rule.name.startsWith("departure")) {
            departureItems.add(myTicket[rule.index!!])
            println("${rule.name} is at index ${rule.index} which is ${myTicket[rule.index!!]}")
        }
    }

    var sum = departureItems.first()
    for (d in 1 until departureItems.size) {
        sum *= d
    }

    println("our sum is $sum")


}

fun dec16_forRuleFindIndexInTicket(rule: TicketRule, tickets: List<List<Int>>) : List<Int> {

    val indexPossibilities = ArrayList<Int>()
    println("trying to find index for ${rule.name}")

    // find the first index in ticket1 that matches
    for (a in tickets[0].indices) {
        val itemA = tickets[0][a]
        if (dec16_doesItemConformToRule(rule, itemA)) {
            // we have an item that's okay!
            // let's check for all the other items at that index

            var correct = true
            // we can skip ticket 0 because that's us
            for (b in 1 until tickets.size) {
                val itemB = tickets[b][a]
                if (!dec16_doesItemConformToRule(rule, itemB)) {
                    correct = false
                }
            }

            if (correct) {
                indexPossibilities.add(a)
            }

        }
    }
    println("found ${indexPossibilities.toString()}")
    return indexPossibilities
}

fun dec16_doesItemConformToRule(rule: TicketRule, item: Int) : Boolean {
    return (item >= rule.firstRange.first && item <= rule.firstRange.second) ||
            (item >= rule.secondRange.first && item <= rule.secondRange.second)
}

fun dec16_readRule(line: String) : TicketRule {

    val parts = line.split(": ")
    val ranges = parts[1].split(" or ")

    val firstRangeParts = ranges[0].split("-")
    val secondRangeParts = ranges[1].split("-")

    val firstRange = Pair(
        Integer.parseInt(firstRangeParts[0]),
        Integer.parseInt(firstRangeParts[1]) )

    val secondRange = Pair(
        Integer.parseInt(secondRangeParts[0]),
        Integer.parseInt(secondRangeParts[1]) )

    return TicketRule(name = parts[0], firstRange, secondRange)
}

fun dec16_readTicket(line: String): List<Int> {
    val ticket = ArrayList<Int>()
    val parts = line.split(",")

    for (p in parts) {
        ticket.add(Integer.parseInt(p))
    }

    return ticket
}

// departure location: 36-269 or 275-973
data class TicketRule(
    val name: String,
    val firstRange: Pair<Int, Int>,
    val secondRange: Pair<Int, Int>,
    var potentialIndexes:ArrayList<Int> = ArrayList(),
    var index:Int? = null)

fun dec16_returnInvalidItemsForTicket(ticket: List<Int>, rules: List<TicketRule>) : List<Int> {
    val invalids = ArrayList<Int>()

    // for each item in the ticket
    for (t in ticket) {

        var correct = false
        // check if it matches a rule
        for (r in rules) {
            // does it pass this rule
            if ( (t >= r.firstRange.first && t <= r.firstRange.second) ||
                (t >= r.secondRange.first && t <= r.secondRange.second)) {
                correct = true
            }
        }

        if (!correct) {
            invalids.add(t)
        }
    }

    return invalids
}
