package de.hsh.kevin.logic;

import common.config.WindowConfig;
import de.hsh.kevin.logic.difficulty.TIDifficulty;
import de.hsh.kevin.logic.difficulty.enmDifficultyOptions;

public class GameField {
    private double width;
    private double height;
    private TIDifficulty diff;

    public GameField() {
	this(TIConfig.getDifficultyOption());
    }
    
    public GameField(enmDifficultyOptions difficulty) {
	diff = new TIDifficulty(difficulty);
	
	width = (int)(500 * diff.getFactor());
	height = WindowConfig.window_height;
    }

    public double getWidth() {
        return width;
    }


    public double getHeight() {
        return height;
    }

}
