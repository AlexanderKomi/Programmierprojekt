package de.hsh.kevin;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.Pane;

import java.util.Observer;

public class KevinGame extends Game {

    public KevinGame( Observer o ) {
        super( o, "Kevin" );
    }

    @Override
    public Pane initGameContentWindow( Observer observer ) {
        return new Pane();
    }
}
