package de.hsh.daniel.model.board

import common.util.Logger
import de.hsh.daniel.model.Card
import de.hsh.daniel.model.Resources
import javafx.application.Platform
import java.util.*

object BoardUtilities {
    /**
     * Initializes cards with number of pairs fetched from menu button
     */
    @JvmStatic
    fun initCards(numberOfPairs: Int): List<Card> {
        val cardList = ArrayList<Card>()
        for (i in 0 until numberOfPairs) {
            val c1 = Card(Resources.cardImages[i],
                          i)
            val c2 = Card(Resources.cardImages[i],
                          i)
            cardList.add(c1)
            cardList.add(c2)
        }
        cardList.shuffle()
        return cardList
    }

    /**
     * Creates card grid
     */
    @JvmStatic
    fun createGrid(cardList: List<Card>,
                   numberOfPairs: Byte,
                   gridW: Double,
                   gridH: Double,
                   imgSize: Double,
                   spacing: Double): List<Card> {
        val xStart: Int
        val xStartReset: Int
        when {
            numberOfPairs.toInt() == 6 -> {
                xStart = 300
                xStartReset = 300
            }
            numberOfPairs.toInt() == 8 -> {
                xStart = 200
                xStartReset = 200
            }
            else                       -> {
                xStart = 30
                xStartReset = 30
            }
        }
        return adjustCardPositions(cardList, xStart, xStartReset, gridW,
                                   gridH,
                                   imgSize,
                                   spacing)
    }

    /**
     * Adjusts card positions depending on grid sizing/ number of cards.
     * @param cardList
     * @param xStart
     * @param xStartReset
     * @param gridW
     * @param gridH
     * @param imgSize
     * @param spacing
     * @return
     */
    private fun adjustCardPositions(
            cardList: List<Card>,
            xStart: Int,
            xStartReset: Int,
            gridW: Double,
            gridH: Double,
            imgSize: Double,
            spacing: Double
                                   ): List<Card> {
        var xStart = xStart
        var yStart = 10
        var imgCount = 0
        var j = 0
        while (j < gridH) {
            var k = 0
            while (k < gridW) {
                val i = cardList[imgCount]
                i.setPos(xStart.toDouble(), yStart.toDouble())
                i.width = imgSize
                i.height = imgSize
                xStart += (imgSize + spacing).toInt()
                imgCount++
                k++
            }
            yStart += (imgSize + spacing / 2).toInt()
            xStart = xStartReset
            j++
        }
        return cardList
    }

    /**
     * Delays time for given amount
     *
     * @param time
     * is converted from sec. to millis.
     */
    @JvmStatic
    fun delay(time: Int) {
        Platform.runLater {
            Logger.log("DELAY USED")
            try {
                Thread.sleep(time * 1000.toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}
