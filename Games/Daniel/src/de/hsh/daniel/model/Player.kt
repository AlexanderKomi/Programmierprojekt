package de.hsh.daniel.model

class Player(var name: String) {

    var points: Int = 0
    var isMyTurn = false

    /**
     * Increments players points
     */
    fun incrementPoints() {
        points++
    }

    fun setTurn(turn: Boolean) {
        isMyTurn = turn
    }

}
