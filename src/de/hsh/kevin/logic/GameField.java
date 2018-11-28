package de.hsh.kevin.logic;

import common.config.WindowConfig;

public class GameField {
    private int width;
    private int height;
    private TIDifficulty diff;
    
    private int x = 0;
    private int y = 0;
    
    public GameField() {
	diff = new TIDifficulty();
	width = (int)(WindowConfig.window_width / 2 * diff.getFactor());
	height = WindowConfig.window_height;
	x = WindowConfig.window_width / 2;
	y = 0;
    }
    
    public GameField(TIDifficulty difficulty) {
	diff = difficulty;
	
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
    

}
