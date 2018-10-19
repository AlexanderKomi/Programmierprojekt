package de.hsh.alexander.engine.game;

import javafx.scene.layout.Pane;

import java.util.Observer;

public abstract class Game extends java.util.Observable implements GameInterface {

    private Pane     gameContentPane;
    private Observer observer;

    protected Game( Observer o ) {
        this.observer = o;
        this.gameContentPane = initGameContentWindow();
    }

    public void notifyObservers() {
        this.observer.update( this, gameContentPane );
    }

    @Override
    public void notifyObservers( Object arg ) {
        this.observer.update( this, arg );
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj instanceof Game ) {
            Game g = (Game) obj;
            return g.observer.equals( this.observer ) &&
                   g.gameContentPane.equals( this.observer );
        }
        return false;
    }

    // ----------------------------------- GETTER & SETTER  -----------------------------------

    public Pane getGameContentPane() {
        return gameContentPane;
    }

    public void setGameContentPane( Pane gameContentPane ) {
        this.gameContentPane = gameContentPane;
    }
}
