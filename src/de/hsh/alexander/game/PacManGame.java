package de.hsh.alexander.game;

import javafx.scene.canvas.Canvas;

import java.util.Observable;

public class PacManGame extends Observable {

    private Canvas canvas = new Canvas();

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas( Canvas canvas ) {
        this.canvas = canvas;
    }
}
