package de.hsh.dennis.model;

import common.actor.Direction;
import common.util.Logger;
import de.hsh.dennis.model.actors.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class GameModel {

    private Canvas canvas;
    GraphicsContext gc;

    private Player player = new Player();

    public void userInput(KeyCode k) {
        switch (k) {
            case W:
                player.changeSkin(Direction.Up);
                break;
            case UP:
                player.changeSkin(Direction.Up);
                break;
            case A:
                player.changeSkin(Direction.Left);
                break;
            case LEFT:
                player.changeSkin(Direction.Left);
                break;
            case S:
                player.changeSkin(Direction.Down);
                break;
            case DOWN:
                player.changeSkin(Direction.Down);
                break;
            case D:
                player.changeSkin(Direction.Right);
                break;
            case RIGHT:
                player.changeSkin(Direction.Right);
                break;
            default:
                Logger.log("unbidden Key pressed: " + k.getName());
        }
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
        gc = this.canvas.getGraphicsContext2D();
    }

    public void render() {
        gc.drawImage(player.getSkin_current(), player.getPosX(), player.getPosY());
    }
}
