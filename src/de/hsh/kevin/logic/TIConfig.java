package de.hsh.kevin.logic;

import common.util.Path;

public class TIConfig {

    public static final String resLocation = Path.getExecutionLocation() + "de/hsh/kevin/res/";
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

}
