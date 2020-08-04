package de.hsh.kevin.logic

/**
 * Einstellungen des Spiels
 * @author Kevin
 */
object Config {
    const val resLocation = "/de/hsh/kevin/res/"
    private const val paketSpawnDelay = 100

    /**
     * Liefert aktuelle SoundOption
     * @return aktuelle SoundOption
     */
    var soundOption = enmSoundOptions.off
        private set

    /**
     * Liefert aktuelle Schwierigkeit
     * @return aktuelle Schwierigkeit
     */
    var difficultyOption = enmDifficultyOptions.easy
        private set

    /**
     * Ändert die Soundoption von on zu off und umgekehrt
     */
    @JvmStatic
    fun switchSound() {
        soundOption = if (soundOption === enmSoundOptions.off) {
            enmSoundOptions.on
        } else {
            enmSoundOptions.off
        }
    }

    /**
     * Setzt perset für die Schwierigkeit
     */
    @JvmStatic
    fun setDifficulyPreset() {
        difficultyOption = enmDifficultyOptions.easy
    }

    /**
     * Setzt preset für den Sound
     */
    @JvmStatic
    fun setSoundPreset() {
        soundOption = enmSoundOptions.off
    }

    /**
     * Wechselt zur nächsten Schwierigkeit
     */
    @JvmStatic
    fun switchDifficulty() {
        difficultyOption = when {
            difficultyOption === enmDifficultyOptions.easy   -> enmDifficultyOptions.normal
            difficultyOption === enmDifficultyOptions.normal -> enmDifficultyOptions.hard
            else                                             -> enmDifficultyOptions.easy
        }
    }

    /**
     * Liefert Faktor für aktuelle Schwierigkeit, mit easy als Standard und andere mit höherem Wert
     * @return
     */
    @JvmStatic
    val difficultyFactor: Double
        get() = when (difficultyOption) {
            enmDifficultyOptions.easy   -> 1.0
            enmDifficultyOptions.normal -> 1.25
            enmDifficultyOptions.hard   -> 1.5
        }


    val maxSpawnCount: Int
        get() = when (difficultyOption) {
            enmDifficultyOptions.easy   -> 2
            enmDifficultyOptions.normal -> 3
            enmDifficultyOptions.hard   -> 4
        }

    fun getPaketSpawnDelay(): Int = when (difficultyOption) {
        enmDifficultyOptions.easy   -> paketSpawnDelay
        enmDifficultyOptions.normal -> (paketSpawnDelay * 1.25).toInt()
        enmDifficultyOptions.hard   -> (paketSpawnDelay * 1.25).toInt()
    }
}
