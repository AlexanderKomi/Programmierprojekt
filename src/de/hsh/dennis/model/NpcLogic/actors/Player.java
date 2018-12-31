package de.hsh.dennis.model.NpcLogic.actors;

import common.actor.Actor;
import common.actor.Direction;
import de.hsh.dennis.model.NpcLogic.SkinConfig;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;

public class Player extends Actor {

    //Visuals
    private static final String    pictureFileName  = "/de/hsh/dennis/resources/actors/player/player_standard.png";

    private              Direction currentDirection = Direction.Non;

    public Player() throws FileNotFoundException {
        super(pictureFileName, SkinConfig.Player.posX, SkinConfig.Player.posY);
        setSkinToDefault();
    }

    public void changeSkin(Direction dir) {
        switch (dir) {
            case Left:
                setSkin(SkinConfig.Player.skin_hit_left);
                setDirection(Direction.Left);
                break;
            case Right:
                setSkin(SkinConfig.Player.skin_hit_right);
                setDirection(Direction.Right);
                break;
            case Up:
                if(this.getSkin() == SkinConfig.Player.skin_standard_right){
                    setSkin(SkinConfig.Player.skin_up_right);
                }else{
                    setSkin(SkinConfig.Player.skin_up_left);
                }
                setDirection(Direction.Up);
                break;
            case Down:
                if(this.getSkin() == SkinConfig.Player.skin_standard_right){
                    setSkin(SkinConfig.Player.skin_down_right);
                }else{
                    setSkin(SkinConfig.Player.skin_down_left);
                }
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
        if(     this.getSkin() == SkinConfig.Player.skin_hit_right  ||
                this.getSkin() == SkinConfig.Player.skin_up_right   ||
                this.getSkin() == SkinConfig.Player.skin_down_right )
        {       setCurrentImage(SkinConfig.Player.skin_standard_right); }
        else{   setCurrentImage(SkinConfig.Player.skin_standard_left);  }

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
