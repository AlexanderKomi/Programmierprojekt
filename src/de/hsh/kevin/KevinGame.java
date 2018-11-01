package de.hsh.kevin;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

public class KevinGame extends Game {

    public KevinGame( Observer o ) {
        super( o, "Kevin" );
    }

    @Override
    public void update( Observable o, Object arg ) {

    }

    @Override
    public Pane initGameContentWindow( Observer observer ) {
        return new Pane();
    }
}
