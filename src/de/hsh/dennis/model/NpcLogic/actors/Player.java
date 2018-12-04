package de.hsh.dennis.model.NpcLogic.actors;

import common.actor.Actor;
import common.actor.Direction;
import common.util.Path;
import de.hsh.dennis.model.NpcLogic.Config;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;

public class Player extends Actor {

    //Visuals
    private static final String pictureFileName = Path.getExecutionLocation() + "de/hsh/dennis/resources/actors/Player/player_standard.png";
    private Direction currentDirection = Direction.Non;

    public Player() throws FileNotFoundException {
        super(pictureFileName, Config.Player.posX, Config.Player.posY);
        setSkinToDefault();
    }

    public void changeSkin(Direction dir) {
        switch (dir) {
            case Left:
                setSkin(Config.Player.skin_left);
                setDirection(Direction.Left);
                break;
            case Right:
                setSkin(Config.Player.skin_right);
                setDirection(Direction.Right);
                break;
            case Up:
                setSkin(Config.Player.skin_up);
                setDirection(Direction.Up);
                break;
            case Down:
                setSkin(Config.Player.skin_down);
                setDirection(Direction.Down);
                break;

        }
    }

    // --- Getter & Setter ------------------------------------------------------------------------

    public void setDirection(Direction dir) {
        this.currentDirection = dir;
    }

    public Direction getDirection() {
        return this.currentDirection;
    }

    public void setSkinToDefault() {
        setCurrentImage(Config.Player.skin_standard);
        setDirection(Direction.Non);
    }

    public void setSkin(Image img) {
        setCurrentImage(img);
    }

    public Image getSkin() {
        return this.getCurrentImage();
    }

    public double getPosX() {
        return this.getX();
    }

    public double getPosY() {
        return this.getY();
    }

}