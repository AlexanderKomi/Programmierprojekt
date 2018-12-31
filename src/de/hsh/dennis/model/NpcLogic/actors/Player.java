package de.hsh.dennis.model.NpcLogic.actors;

import common.actor.Actor;
import common.actor.Direction;
import de.hsh.dennis.model.NpcLogic.SkinConfig;
import javafx.scene.image.Image;

import static de.hsh.dennis.model.NpcLogic.SkinConfig.Player.*;

public class Player extends Actor {

    private              Direction currentDirection = Direction.Non;


    public Player() {
        super( SkinConfig.Player.skin_standard_path, SkinConfig.Player.posX, SkinConfig.Player.posY );
        setSkinToDefault();
    }

    public void changeSkin(Direction dir) {
        switch (dir) {
            case Left:
                setSkin( skin_hit_left_path );
                setDirection(Direction.Left);
                break;
            case Right:
                setSkin( skin_hit_right_path );
                setDirection(Direction.Right);
                break;
            case Up:
                setSkin( skin_up_left_path );
                setDirection(Direction.Up);
                break;
            case Down:
                setSkin( skin_down_left_path );
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
        setCurrentImage( skin_standard_left_path );
        setDirection(Direction.Non);
    }

    private void setSkin( final String imgPath ) {
        setCurrentImage( imgPath );
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
