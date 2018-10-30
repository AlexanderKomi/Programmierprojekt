package de.hsh.amir;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.Pane;

import java.util.Observer;

/**
 * Created by 424-ml6-u1 on 30.10.18.
 */
public class AmirsGame extends Game {

    protected AmirsGame( Observer o, String name ) {
        super( o, name );
    }

    @Override
    public Pane initGameContentWindow( Observer observer ) {
        return new Pane();
    }
}
