package de.hsh.kevin.logic;

import de.hsh.kevin.logic.difficulty.enmDifficultyOptions;

public class TIConfig {

    private static enmSoundOptions soundConfig = enmSoundOptions.off;
    private static enmDifficultyOptions diffConfig = enmDifficultyOptions.easy;
    public static int maxLife = 5;

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

}
