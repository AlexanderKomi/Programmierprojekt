package de.hsh.Examples;

import common.events.KeyEventManager;
import common.events.MouseEventManager;
import common.updates.UpdateCodes;
import de.hsh.alexander.engine.game.Game;

import java.util.Observable;
import java.util.Observer;

public class ExampleGame extends Game
{

    private ExampleFXMLLoading efl;


    protected ExampleGame(Observer o, String name) {
        super(o, "ExampleGame_Name");
        efl = new ExampleFXMLLoading(this);
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

    @Override
    public void exitToMainGUI() {
        setChanged();
        notifyObservers( UpdateCodes.Dennis.exitToMainGui );
    }
}
