package de.hsh.daniel.model

import common.actor.Actor
import common.util.PlaySound

class Card(private val pictureFileName: String, pair_id: Int) : Actor(pictureFileName) {
    /**
     * *****************************************************************
     * *                                                               *
     * *                       GETTERS & SETTERS                       *
     * *                                                               *
     * *****************************************************************
     *
     */
    private val pairId: Int
    var isCardMatched = false
    var isTurned = true
    override fun toString(): String = "pair_id: $pairId"

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null) {
            return false
        }
        if (javaClass != other.javaClass) {
            return false
        }
        val otherCard = other as Card
        return pairId == otherCard.pairId
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + pairId
        return result
    }

    /**
     * Displays front card image and plays sound when "turning" card.
     */
    fun turn() {
        val backupWidth = width
        val backupHeight = height
        when {
            isTurned -> {
                PlaySound.playSound(cardFrontSoundPath)
                setCurrentImage(pictureFileName)
                isTurned = false
            }
            !isTurned -> {
                PlaySound.playSound(cardBackSoundPath)
                setCurrentImage(Resources.cardback)
                isTurned = true
            }
        }
        width = backupWidth
        height = backupHeight
    }

    companion object {
        private const val cardFrontSoundPath = "/de/hsh/daniel/resources/cardFlip1.wav"
        private const val cardBackSoundPath = "/de/hsh/daniel/resources/cardFlip2.wav"
    }

    init {
        setCurrentImage(Resources.cardback)
        this.pairId = pair_id
    }
}
