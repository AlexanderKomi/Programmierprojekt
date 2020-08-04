package de.hsh.daniel.model

import common.actor.Actor
import common.util.Logger
import common.util.PlaySound

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
        return "pair_id: " + pair_id
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj == null) {
            return false
        }
        if (javaClass != obj.javaClass) {
            return false
        }
        val other = obj as Card
        return pair_id == other.pair_id
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

    /**
     * Card constructor
     * @param pictureFileName: Path to image for card
     * @param pair_id
     */
    init {
        setCurrentImage(Resources.cardback)
        this.pair_id = pair_id
    }
}
