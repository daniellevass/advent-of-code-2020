import java.io.File

fun main(args: Array<String>) {

    val initialGame = dec22_readFile("src/main/resources/dec22_input.txt")

    val finalTotal = dec22_playGame(initialGame)
    println("we got $finalTotal at the end")

}

fun dec22_readFile(filename: String) : Pair<List<Int>, List<Int>> {

    val player1 = ArrayList<Int>()
    val player2 = ArrayList<Int>()

    var isPlayer1 = true

    File(filename)
        .forEachLine {

            if (it.length == 0) {
                // next player
                isPlayer1 = false
            }else if (it.contains("Player")) {
                // ignore
            } else {

                val card = it.toInt()

                if (isPlayer1) {
                    player1.add(card)
                } else {
                    player2.add(card)
                }
            }
        }

    return Pair(player1, player2)
}

fun dec22_processRound(player1: List<Int>, player2: List<Int>) : Pair<List<Int>, List<Int>> {

    val player1NewCards = ArrayList<Int>()
    val player2NewCards = ArrayList<Int>()

    // add all the cards except the top item
    player1NewCards.addAll(player1.subList(1, player1.size))
    player2NewCards.addAll(player2.subList(1, player2.size))

    // take the top cards from each
    if (player1.first() > player2.first()) {
        // player 1 won
        player1NewCards.add(player1.first())
        player1NewCards.add(player2.first())
    } else {
        //player 2 won
        player2NewCards.add(player2.first())
        player2NewCards.add(player1.first())
    }

    return Pair(player1NewCards, player2NewCards.toList())
}

fun dec22_playGame(initialGame: Pair<List<Int>, List<Int>>) : Int{

    var completed = false
    var currentGame = initialGame

    while(!completed) {

        val nextRound = dec22_processRound(currentGame.first, currentGame.second)
        currentGame = nextRound

        // check if either player has zero cards
        if (currentGame.first.isEmpty()
            || currentGame.second.isEmpty()) {
            completed = true
        }
    }

    return if (currentGame.first.isNotEmpty()) {
        // player 1 won
        dec22_calculatePlayersTotal(currentGame.first)
    } else {
        dec22_calculatePlayersTotal(currentGame.second)
    }
}

fun dec22_calculatePlayersTotal(player: List<Int>) : Int {

    var total = 0
    var i = player.size
    for(card in player) {
        total += (i*card)
        i--
    }

    return total
}