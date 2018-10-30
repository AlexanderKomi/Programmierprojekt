package de.hsh.daniel;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.Pane;

import java.util.Observer;

public class RAM extends Game {
    protected RAM( Observer o, String name ) {
        super( o, name );
    }

    @Override
    public Pane initGameContentWindow( Observer observer ) {
        return null;
    }
}
