package de.hsh.alexander.game;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.Observer;

public class PacManCoop extends Game {
    protected PacManCoop( Observer o ) {
        super( o );
    }

    @Override
    protected Node createGameContent() {
        return null;
    }

    @Override
    public Pane initGameContentWindow() {
        return null;
    }
}
