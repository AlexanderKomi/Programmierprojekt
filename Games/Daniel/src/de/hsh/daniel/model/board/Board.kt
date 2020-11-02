package de.hsh.daniel.model.board

import common.util.Logger.log
import de.hsh.daniel.model.Card
import de.hsh.daniel.model.Player
import de.hsh.daniel.model.board.BoardUtilities.delay
import de.hsh.daniel.model.board.BoardUtilities.initCards

/**
 * Class represents Gameboard where Cards are laid out
 */
open class Board internal constructor() {
    /**
     * Checks if one or two cards are selected and determines which card
     * should be turned.
     *
     * @param card
     */
    fun matchCards(card: Card) {
        log("P1 ACTIVE: " + p1!!.isMyTurn)
        log("""
    P2 ACTIVE: ${p2!!.isMyTurn}
    
    """.trimIndent())
        if (!card.isCardMatched && card.isTurned) {
            when {
                !firstCardClicked -> {
                    c1 = card
                    firstCardClicked = true
                }
                firstCardClicked && card === c1 -> {
                    log("SAME CARD SELECTED")
                    return
                }
                else -> c2 = card
            }
            if (c1 != null && c2 == null) {
                c1!!.turn()
            } else if (c1 != null) {
                c2!!.turn()
            }
        }
        checkMatch()
    }

    /**
     * Checks if two cards match and resets cards after check
     * If two cards selected then cards are checked if they match
     */
    private fun checkMatch() {
        if (c1 == null || c2 == null) {
            log("One card empty. \n ")
            return
        }
        when (c2) {
            c1!! -> {
                log("CARDS MATCH")
                setCardsMatched()
                ++matchCount
                givePoints()
                nullCards()
            }
            else -> changeActivePlayer()
        }
    }

    /**
     * Changes active player if cards we're not matched
     */
    private fun changeActivePlayer() {
        log("CARDS DON'T MATCH")
        if (p1!!.isMyTurn) {
            p1!!.setTurn(false)
            p2!!.setTurn(true)
            log("IT'S P2s TURN! NOW")
        } else {
            p2!!.setTurn(false)
            p1!!.setTurn(true)
            log("IT'S P1s TURN! NOW")
        }
    }

    /**
     * Determines winner from collected points
     */
    private fun announceWinner() {
        when {
            p1!!.points > p2!!.points -> winner = p1
            p1!!.points < p2!!.points -> winner = p2
            p1!!.points == p2!!.points -> {
                winner = p1
                winner!!.name = "BOTH"
                //winner.setDraw(p1.getPoints());
            }
        }
        log("P1: " + p1!!.points + "| P2:" + p2!!.points)
    }

    /**
     * Locks matched cards so that they can not be turned anymore
     */
    private fun setCardsMatched() {
        c1!!.isCardMatched = true
        c2!!.isCardMatched = true
    }

    /**
     * Turns both selected cards face down again
     */
    fun turnBackCards() {
        c1!!.turn()
        c2!!.turn()
        delay(2)
    }

    /**
     * Sets selected cards to null
     */
    fun nullCards() {
        c1 = null
        c2 = null
        firstCardClicked = false
    }

    /**
     * Gives points to player who found a pair of cards
     */
    private fun givePoints() {
        if (p1!!.isMyTurn) {
            p1!!.incrementPoints()
            log("""P1 points: ${p1!!.points}""")
        } else if (p2!!.isMyTurn) {
            p2!!.incrementPoints()
            log("""P2 points: ${p2!!.points}""")
        }
        if (matchCount == numberOfPairs.toInt()) {
            announceWinner()
            log(winner!!.name + " WINS")
        }
    }

    val board: Board
        get() = this

    var winner: Player?

    val p1Points: Int
        get() = p1!!.points

    val p2Points: Int
        get() = p2!!.points

    val isP1Turn: Boolean
        get() = p1!!.isMyTurn

    val isP2Turn: Boolean
        get() = p2!!.isMyTurn

    companion object {
        @JvmField
        var numberOfPairs: Byte = 0 // These are set externally from the menu.
        private var c1: Card? = null
        var c2: Card? = null
        private var firstCardClicked = false
        var cardList: List<Card> = listOf()
        private var p1: Player? = null
        private var p2: Player? = null
        var winner: Player? = null
        private var matchCount: Int = 0
        @JvmStatic
        fun reset() {
            c1 = null
            c2 = null
            p1 = null
            p2 = null
            winner = null
            matchCount = 0
        }
    }

    init {
        reset()
        cardList = initCards(numberOfPairs.toInt())
        matchCount = 0
        p1 = Player("P1")
        p2 = Player("P2")
        winner = null
        p1!!.setTurn(true)
    }
}
