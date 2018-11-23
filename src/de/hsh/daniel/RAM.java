package de.hsh.daniel;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.events.KeyEventManager;
import common.events.MouseEventManager;

import java.util.Observable;
import java.util.Observer;

public class RAM extends GameEntryPoint {

    private RAMFxmlChanger changer;

    public RAM( Observer o ) {
        super( o, WindowConfig.daniel_title );
        //TODO: implement
        // changer = new RAMFxmlChanger(this, "path.fxml", new Controller());
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
