package de.hsh.amir.logic;

import common.actor.Direction;
import common.util.Logger;
import javafx.scene.canvas.Canvas;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class AmirGame {
    private Spielfigur spielfigur;
    private Gegner gegner;

    public void initializePlayer(Canvas canvas) throws FileNotFoundException {
        HashMap<String, Direction> playerKeyMap = new HashMap<>();
        playerKeyMap.put("Right", Direction.Right);
        playerKeyMap.put("Left", Direction.Left);
        spielfigur = new Spielfigur("de/hsh/amir/resources/playerFigur.png", playerKeyMap);
    }

    public void render(Canvas canvas, int fps) {
        Logger.log("render");
    }

}
