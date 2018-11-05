package de.hsh.dennis;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

public class DennisGame extends Game {

    public DennisGame( Observer o ) {
        super( o, "Dennis" );
        this.setGameContentPane( new Pane() );
    }

    @Override
    public void update( Observable o, Object arg ) {

    }
}
