package de.hsh.alexander.game.actor;

import de.hsh.alexander.util.Path;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PacMan {

    private Image picture;

    public PacMan() {
        loadPicture();
    }

    private void loadPicture() {
        try {
            String location = Path.getExecutionLocation() + "de/hsh/alexander/game/actor/" + "Bug.png";
            picture = new Image( new FileInputStream( location ) );
        }
        catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }
    }


    public Image getPicture() {
        return picture;
    }

    public void setPicture( Image picture ) {
        this.picture = picture;
    }
}
