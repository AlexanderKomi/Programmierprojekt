package de.hsh.amir.logic;

import common.util.Logger;
import javafx.scene.canvas.Canvas;

public class AmirGame {
    private Spielfigur spielfigur;
    private Gegner gegner;

    public void render(Canvas canvas, int fps) {
        spielfigur.draw(canvas);
        Logger.log("render");
    }

}
