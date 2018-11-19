package de.hsh.Examples;

import common.config.WindowConfig;
import common.updates.UpdateCodes;
import de.hsh.alexander.engine.game.Game;
import de.hsh.dennis.controller.BigBrother;

import java.util.Observable;
import java.util.Observer;

public class ExampleGame extends Game
{

    private ExampleFXMLLoading efl;


    protected ExampleGame(Observer o, String name) {
        super(o, "ExampleGame_Name");
        efl = new ExampleFXMLLoading(this);
    }

    public void exitToMainGui(){
        setChanged();
        notifyObservers(UpdateCodes.Dennis.exitToMainGui);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
