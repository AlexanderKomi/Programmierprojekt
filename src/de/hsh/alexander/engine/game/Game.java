package de.hsh.alexander.engine.game;

import javafx.scene.layout.Pane;

import java.util.Observer;

public abstract class Game extends java.util.Observable {

    public final String name;
    private      Pane   gameContentPane;

    protected Game( Observer o, String name ) {
        this.name = name;
        this.addObserver( o );
        this.gameContentPane = initGameContentWindow( o );
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


    public abstract Pane initGameContentWindow( Observer observer );

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
