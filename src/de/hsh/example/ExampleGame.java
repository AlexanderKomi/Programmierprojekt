package de.hsh.example;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

public class ExampleGame extends Game {

    public ExampleGame( Observer o ) {
        super( o, "Example" );
    }

    @Override
    public Pane initGameContentWindow( Observer observer ) {
        return new Pane();
    }

    @Override
    public void update( Observable o, Object arg ) {

    }
}