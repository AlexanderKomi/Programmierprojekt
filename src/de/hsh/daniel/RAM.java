package de.hsh.daniel;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

public class RAM extends Game {
    public RAM( Observer o ) {
        super( o, "Daniel" );
    }

    @Override
    public Pane initGameContentWindow( Observer observer ) {
        return new Pane();
    }

    @Override
    public void update( Observable o, Object arg ) {

    }
}
