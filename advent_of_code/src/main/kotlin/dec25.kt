fun main(args: Array<String>) {

    val cardPublicKey = 9093927L
    val cardLoopSize = dec25_findLoopSize(publicKey = cardPublicKey, 7L)
//    println("loopsize = $cardLoopSize")

    val doorPublicKey = 11001876L
    val doorLoopSize = dec25_findLoopSize(doorPublicKey, 7L)
//    println("door loop size = $doorLoopSize")

    /**
     * Transforming the subject number of 17807724 (the door's public key)
     * with a loop size of 8 (the card's loop size) produces the encryption key, 14897079.
     * (Transforming the subject number of 5764801 (the card's public key) with a loop size of
     * 11 (the door's loop size) produces the same encryption key: 14897079.)
     */
    var encryptionKey = dec25_calculateEncryptionKey(cardPublicKey, doorLoopSize)
    println("encryption key = $encryptionKey")

}


fun dec25_transformSubjectNumber(number: Long, initialKey: Long): Long {
    return (number * initialKey) % 20201227
}

fun dec25_findLoopSize(publicKey: Long, initialKey: Long) : Int {
    var loopSize = 1
    var completed = false
    var subjectKey = initialKey
    while(!completed) {
        subjectKey = dec25_transformSubjectNumber(subjectKey, initialKey)
        loopSize ++
        if (subjectKey == publicKey) {
            completed = true
        }
//        println("public key = $subjectKey")
    }

    return loopSize
}

fun dec25_calculateEncryptionKey(subjectNumber: Long, loopSize: Int) : Long {
    var key = subjectNumber
    for (i in 1 until loopSize) {
        key = dec25_transformSubjectNumber(number = key, subjectNumber)
//        println("encryption key = $key")
    }

    return key
}