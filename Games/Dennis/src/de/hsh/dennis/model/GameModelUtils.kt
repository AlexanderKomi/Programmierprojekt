package de.hsh.dennis.model

import de.hsh.dennis.model.Spawn.NpcHandler
import de.hsh.dennis.model.audio.AudioAnalyser
import de.hsh.dennis.model.audio.AudioConfig.*

internal object GameModelUtils {

    private fun NpcHandler.initDifficulty(
            sensitivity: Double,
            spawnDelay: Double,
            audioFileName: String,
            movingSpeed: Double
    ): Double {
        AudioAnalyser.sensitivity = sensitivity
        AudioAnalyser.spawnDelay = spawnDelay
        this.generateNpcs(audioFileName, movingSpeed)
        MusicPlayer.loadFile(audioFileName)
        return movingSpeed
    }

    fun initEasyDifficulty(npcHandler: NpcHandler): Double  = npcHandler.initDifficulty(
            0.2,
            DelayBetweenSpawns._easy,
            Mp3Names.easy,
            MovingSpeeds._easy
    )

    fun initMediumDifficulty(npcHandler: NpcHandler): Double  = npcHandler.initDifficulty(
            0.1,
            DelayBetweenSpawns._medium,
            Mp3Names.medium,
            MovingSpeeds._medium
    )

    fun initHardDifficulty(npcHandler: NpcHandler): Double  = npcHandler.initDifficulty(
            0.05,
            DelayBetweenSpawns._hard,
            Mp3Names.hard,
            MovingSpeeds._hard
    )

    fun initNightmareDifficulty(npcHandler: NpcHandler): Double  = npcHandler.initDifficulty(
            0.01,
            DelayBetweenSpawns._nightmare,
            Mp3Names.nightmare,
            MovingSpeeds._nightmare
    )
}
