package de.hsh.kevin.logic

private const val maxLife = 4

/**
 * Liefert Faktor für die Leben je nach Schwierigkeit, mit easy als Standard und andere mit niedrigem Wert
 * @return
 */
private val lifeFactor: Double
    get() = when (Config.difficultyOption) {
        enmDifficultyOptions.easy   -> 1.0
        enmDifficultyOptions.normal -> 0.75
        enmDifficultyOptions.hard   -> 0.5
    }

/**
 * Leben erstellt ein Counter
 * @author Kevin
 */
data class Leben(var leben: Int = (lifeFactor * maxLife).toInt()) {
    /**
     * Verringert counter um 1
     */
    fun decrease() {
        leben--
    }



}
