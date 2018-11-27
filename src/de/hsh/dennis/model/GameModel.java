package de.hsh.dennis.model;

import common.actor.Direction;
import common.util.Logger;
import de.hsh.dennis.model.KeyLayout.Movement.Custom;
import de.hsh.dennis.model.actors.Player;
import de.hsh.dennis.model.audio.AudioPlayer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class GameModel {

    private Canvas canvas;
    private GraphicsContext gc;

    private int Score = 0;
    private int Health = 100;

    //animation timing values
    private double animationDelay = 0.5; //animation delay in seconds
    private long skinResetTimer;
    private boolean reset = false;

    private Player player = new Player();

    //Audio Stuff
    private boolean musikOn = true;
    private AudioPlayer aa = new AudioPlayer();

    public void userInput(KeyCode k) {

        if (k == Custom.UP || k == Custom.UP_ALT) {
            player.changeSkin(Direction.Up);
            setResetTimer();
            aa.pause(); //just for debugging
            return;
        } else if (k == Custom.LEFT || k == Custom.LEFT_ALT) {
            player.changeSkin(Direction.Left);
            setResetTimer();
            return;
        } else if (k == Custom.DOWN || k == Custom.DOWN_ALT) {
            player.changeSkin(Direction.Down);
            setResetTimer();
            aa.resume(); //just for debugging
            return;
        } else if (k == Custom.RIGHT || k == Custom.RIGHT_ALT) {
            player.changeSkin(Direction.Right);
            setResetTimer();
            return;
        }
        Logger.log("unbidden Key Input \'" + k + "\'");
    }

    private void setResetTimer() {
        skinResetTimer = System.currentTimeMillis();
        reset = true;
    }

    private void restetSkin() {
        if (reset) {
            double elapsedTime = System.currentTimeMillis() - skinResetTimer;
            double elapsedSeconds = elapsedTime / 1000;
            if (elapsedSeconds >= animationDelay) {
                player.setSkinToDefault();
                reset = false;
            }
        }
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
        gc = this.canvas.getGraphicsContext2D();
    }

    public void render() {
        if (musikOn) {
            musikOn = false;
            aa.loadFile(this.getClass().getResource("audio/jingle.mp3").getPath());
            aa.play();


        }
        clearCanvas();
        restetSkin();
        gc.drawImage(player.getSkin_current(), player.getPosX(), player.getPosY());

    }

    private void clearCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
