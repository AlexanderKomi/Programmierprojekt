package de.hsh.dennis.model;

import common.actor.Direction;
import common.util.Logger;
import de.hsh.dennis.model.actors.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class GameModel {

    private Canvas canvas;
    private GraphicsContext gc;

    //animation timing values
    private double animationDelay = 0.5; //animation delay in seconds
    private long skinResetTimer;
    private boolean reset = false;

    private Player player = new Player();

    public void userInput(KeyCode k) {
        switch (k) {
            case W:
                player.changeSkin(Direction.Up);
                setResetTimer();
                break;
            case UP:
                player.changeSkin(Direction.Up);
                setResetTimer();
                break;
            case A:
                player.changeSkin(Direction.Left);
                setResetTimer();
                break;
            case LEFT:
                player.changeSkin(Direction.Left);
                setResetTimer();
                break;
            case S:
                player.changeSkin(Direction.Down);
                setResetTimer();
                break;
            case DOWN:
                player.changeSkin(Direction.Down);
                setResetTimer();
                break;
            case D:
                player.changeSkin(Direction.Right);
                setResetTimer();
                break;
            case RIGHT:
                player.changeSkin(Direction.Right);
                setResetTimer();
                break;
            default:
                Logger.log("unbidden Key pressed: " + k.getName());
        }
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
        clearCanvas();
        restetSkin();
        gc.drawImage(player.getSkin_current(), player.getPosX(), player.getPosY());

    }

    private void clearCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
