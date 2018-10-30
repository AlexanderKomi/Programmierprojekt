package de.hsh.kevin;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.Pane;

import java.util.Observer;

public class KevinGame extends Game {

    protected KevinGame( Observer o, String name ) {
        super( o, name );
    }

    @Override
    public Pane initGameContentWindow( Observer observer ) {
        return null;
    }
}
