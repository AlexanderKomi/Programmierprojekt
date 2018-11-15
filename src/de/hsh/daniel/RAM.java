package de.hsh.daniel;

import common.config.WindowConfig;
import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

public class RAM extends Game {
    public RAM( Observer o ) {
        super( o, WindowConfig.daniel_title );
        this.setGameContentPane( new Pane() );
    }

    @Override
    public void update( Observable o, Object arg ) {

    }
}
