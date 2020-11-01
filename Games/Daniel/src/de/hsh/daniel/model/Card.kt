package de.hsh.daniel.model

import common.actor.Actor
import common.logger.Logger
import common.sound.PlaySound

class Card(val pictureFileName: String, pair_id: Int) : Actor(pictureFileName) {
    /**
     * *****************************************************************
     * *                                                               *
     * *                       GETTERS & SETTERS                       *
     * *                                                               *
     * *****************************************************************
     *
     */
    val pair_id: Int
    var isCardMatched = false
    var isTurned = true
    override fun toString(): String {
        return "pair_id: $pair_id"
    }

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
        return pair_id == otherCard.pair_id
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + pair_id
        return result
    }

    /**
     * Displays front card image and plays sound when "turning" card.
     */
    fun turn() {
        val backupWidth = width
        val backupHeight = height
        if (isTurned) {
            PlaySound.playSound(cardFrontSoundPath)
            setCurrentImage(pictureFileName)
            isTurned = false
        } else if (!isTurned) {
            PlaySound.playSound(cardBackSoundPath)
            setCurrentImage(Resources.cardback)
            isTurned = true
        } else {
            Logger.log("CARD ALREADY FACEUP")
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
        this.pair_id = pair_id
    }
}
