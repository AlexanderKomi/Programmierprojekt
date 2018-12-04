package de.hsh.kevin.logic;

import common.util.Path;

public class Config {

    public static final String resLocation = Path.getExecutionLocation() + "de/hsh/kevin/res/";
    public static final int paketSpawnDelay = 100;
    public static final int projectileSpawnDelay = 400;
    public static final int maxLife = 4;
    public static final double spawnChanceGtoB = 0.75;

    private static enmSoundOptions soundConfig = enmSoundOptions.off;
    private static enmDifficultyOptions diffConfig = enmDifficultyOptions.easy;

    public static void switchSound() {
        if (soundConfig == enmSoundOptions.off) {
            soundConfig = enmSoundOptions.on;
        } else {
            soundConfig = enmSoundOptions.off;
        }

    }

    public static enmSoundOptions getSoundOption() {
        return soundConfig;
    }

    public static void switchDifficulty() {
        if (diffConfig == enmDifficultyOptions.easy) {
            diffConfig = enmDifficultyOptions.normal;
        } else if (diffConfig == enmDifficultyOptions.normal) {
            diffConfig = enmDifficultyOptions.hard;
        } else {
            diffConfig = enmDifficultyOptions.easy;
        }

    }

    public static enmDifficultyOptions getDifficultyOption() {
        return diffConfig;
    }

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

}
