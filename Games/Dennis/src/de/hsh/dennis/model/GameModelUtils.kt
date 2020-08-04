package de.hsh.dennis.model

import de.hsh.dennis.model.NpcLogic.NpcHandler
import de.hsh.dennis.model.audio.AudioConfig.*

internal object GameModelUtils {
    fun initEasyDifficulty(npcHandler: NpcHandler) {
        npcHandler.audioAnalyzer.sensitivity = (0.2)
        npcHandler.setDelaysBetweenSpawns(DelayBetweenSpawns._easy)
        npcHandler.generateNpcs(Mp3Names.easy, MovingSpeeds._easy)
        AudioPlayer.MusicPlayer.loadFile(Mp3Names.easy)
    }

    fun initMediumDifficulty(npcHandler: NpcHandler) {
        npcHandler.audioAnalyzer.sensitivity = (0.1)
        npcHandler.setDelaysBetweenSpawns(DelayBetweenSpawns._medium)
        npcHandler.generateNpcs(Mp3Names.medium, MovingSpeeds._medium)
        AudioPlayer.MusicPlayer.loadFile(Mp3Names.medium)
    }

    fun initHardDifficulty(npcHandler: NpcHandler) {
        npcHandler.audioAnalyzer.sensitivity = (0.05)
        npcHandler.setDelaysBetweenSpawns(DelayBetweenSpawns._hard)
        npcHandler.generateNpcs(Mp3Names.hard, MovingSpeeds._hard)
        AudioPlayer.MusicPlayer.loadFile(Mp3Names.hard)
    }

    fun initNightmareDifficulty(npcHandler: NpcHandler) {
        npcHandler.audioAnalyzer.sensitivity = (0.0)
        npcHandler.setDelaysBetweenSpawns(DelayBetweenSpawns._nightmare)
        npcHandler.generateNpcs(Mp3Names.nightmare, MovingSpeeds._nightmare)
        AudioPlayer.MusicPlayer.loadFile(Mp3Names.nightmare)
    }
}
