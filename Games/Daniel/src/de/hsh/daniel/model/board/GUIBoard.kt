package de.hsh.daniel.model.board

import common.actor.CollisionCheck.doesCollide
import common.config.WindowConfig
import de.hsh.daniel.model.board.BoardUtilities.createGrid
import javafx.scene.canvas.Canvas

class GUIBoard : Board() {
    /**
     * A mouse click happened to the board.
     */
    fun onMouseClick(mouse_x: Double, mouse_y: Double) {
        cardList.first { card ->
            doesCollide(card, mouse_x, mouse_y)
        }.also { card ->
            matchCards(card)
        }
    }

    fun draw(canvas: Canvas?) {
        for (card in cardList) {
            card.draw(canvas!!)
        }
        if (c2 != null && !c2!!.isCardMatched) {
            turnBackCards()
            nullCards()
        }
    }

    companion object {
        private const val gridH: Byte = 4
        private const val spacing = 40
        private const val imgSize = (WindowConfig.window_height / 4).toDouble() - spacing.toDouble() / 2
    }

    init {
        val gridW = numberOfPairs / 2
        cardList = createGrid(
                cardList,
                numberOfPairs,
                gridW.toDouble(),
                gridH.toDouble(),
                imgSize,
                spacing.toDouble())
    }
}
