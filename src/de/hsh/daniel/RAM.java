package de.hsh.daniel;

import common.config.WindowConfig;
import common.engine.components.game.Game;
import common.events.KeyEventManager;
import common.events.MouseEventManager;

import java.util.Observable;
import java.util.Observer;

public class RAM extends Game {
    public RAM( Observer o ) {
        super( o, WindowConfig.daniel_title );
        //this.setGameContentPane( new Pane() );
    }

    @Override
    public void update( Observable o, Object arg ) {

    }

    @Override
    public void update( KeyEventManager keyEventManager, Object arg ) {

    }

    @Override
    public void update( MouseEventManager mouseEventManagerManager, Object arg ) {

    }

    @Override
    public void render() {

    }
}
