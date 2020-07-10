package de.hsh.kevin.logic;

/**
 * Einstellungen des Spiels
 * @author Kevin
 *
 */
public class Config {

    public static final String resLocation =  "/de/hsh/kevin/res/";
    public static final int paketSpawnDelay = 100;
    public static final int projectileSpawnDelay = 400;
    public static final int maxLife = 4;
    public static final double spawnChanceGtoB = 0.75;

    private static enmSoundOptions soundConfig = enmSoundOptions.off;
    private static enmDifficultyOptions diffConfig = enmDifficultyOptions.easy;

    /**
     * Ändert die Soundoption von on zu off und umgekehrt
     */
    public static void switchSound() {
        if (soundConfig == enmSoundOptions.off) {
            soundConfig = enmSoundOptions.on;
        } else {
            soundConfig = enmSoundOptions.off;
        }

    }

    /**
     * Setzt perset für die Schwierigkeit
     */
    public static void setDifficulyPreset() {
        diffConfig = enmDifficultyOptions.easy;
    }
    
    /**
     * Setzt preset für den Sound
     */
    public static void setSoundPreset() {
        soundConfig = enmSoundOptions.off;
    }
    
    /**
     * Liefert aktuelle SoundOption
     * @return aktuelle SoundOption
     */
    public static enmSoundOptions getSoundOption() {
        return soundConfig;
    }

    /**
     * Wechselt zur nächsten Schwierigkeit
     */
    public static void switchDifficulty() {
        if (diffConfig == enmDifficultyOptions.easy) {
            diffConfig = enmDifficultyOptions.normal;
        } else if (diffConfig == enmDifficultyOptions.normal) {
            diffConfig = enmDifficultyOptions.hard;
        } else {
            diffConfig = enmDifficultyOptions.easy;
        }

    }

    /**
     * Liefert aktuelle Schwierigkeit
     * @return aktuelle Schwierigkeit
     */
    public static enmDifficultyOptions getDifficultyOption() {
        return diffConfig;
    }

    /**
     * Liefert Faktor für aktuelle Schwierigkeit, mit easy als Standard und andere mit höherem Wert
     * @return
     */
    public static double getDifficultyFactor() {
        switch (diffConfig) {
        case easy:
            return 1;
        case normal:
            return 1.25;
        case hard:
            return 1.5;
        default:
            return 1;
        }
    }

    /**
     * Liefert Faktor für die Leben je nach Schwierigkeit, mit easy als Standard und andere mit niedrigem Wert
     * @return
     */
    public static double getLifeFactor() {
        switch (diffConfig) {
        case easy:
            return 1;
        case normal:
            return 0.75;
        case hard:
            return 0.5;
        default:
            return 1;
        }
    }

    public static double getLife() {
        return getLifeFactor() * maxLife;
    }

    public static int getMaxSpawnCount() {
        switch (diffConfig) {
        case easy:
            return 2;
        case normal:
            return 3;
        case hard:
            return 4;
        default:
            return 2;
        }
    }

    public static int getPaketSpawnDelay() {
        switch (diffConfig) {
        case easy:
            return paketSpawnDelay;
        case normal:
            return (int)(paketSpawnDelay * 1.25);
        case hard:
            return (int)(paketSpawnDelay * 1.25);
        default:
            return 2;
        }
    }

}
