package de.hsh.alexander.engine.game;

import common.KeyEventManager;
import javafx.scene.layout.Pane;

import java.util.Observer;

public abstract class Game extends java.util.Observable implements Observer {

    public final String name;
    private      Pane   gameContentPane;

    protected Game( Observer o, String name ) {
        this.name = name;
        this.addObserver( o );
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj instanceof Game ) {
            Game g = (Game) obj;
            return this.name.equals( g.name ) &&
                   g.gameContentPane.equals( this.gameContentPane );
        }
        return false;
    }

    @Override
    public void notifyObservers() {
        this.setChanged();
        super.notifyObservers();
    }

    @Override
    public void notifyObservers( Object arg ) {
        this.setChanged();
        super.notifyObservers( arg );
    }

    public void addObservable( KeyEventManager keyEventManager ) {
        keyEventManager.addObserver( this );
    }

    // ----------------------------------- GETTER & SETTER  -----------------------------------

    public String getName() {
        return name;
    }

    public Pane getGameContentPane() {
        return gameContentPane;
    }

    public void setGameContentPane( Pane gameContentPane ) {
        this.gameContentPane = gameContentPane;
    }
}
