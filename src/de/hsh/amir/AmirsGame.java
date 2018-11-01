package de.hsh.amir;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by 424-ml6-u1 on 30.10.18.
 */
public class AmirsGame extends Game {

    public AmirsGame( Observer o ) {
        super( o, "AmirsGame" );
    }

    @Override
    public Pane initGameContentWindow( Observer observer ) {
        return new Pane();
    }

    @Override
    public void update( Observable o, Object arg ) {

    }
}
