package de.hsh.kevin.logic

/**
 * Counter für den Score
 * @author Kevin
 */
data class Score(var score: Int = 0) {

    fun increase() {
        score++
    }

}
