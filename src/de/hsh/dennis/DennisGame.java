package de.hsh.dennis;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.Pane;

import java.util.Observer;

public class DennisGame extends Game {

    public DennisGame( Observer o ) {
        super( o, "Dennis" );
    }

    @Override
    public Pane initGameContentWindow( Observer observer ) {
        return new Pane();
    }
}
