package de.hsh.dennis;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.Pane;

import java.util.Observer;

public class DennisGame extends Game {

    protected DennisGame( Observer o, String name ) {
        super( o, name );
    }

    @Override
    public Pane initGameContentWindow( Observer observer ) {
        return null;
    }
}
