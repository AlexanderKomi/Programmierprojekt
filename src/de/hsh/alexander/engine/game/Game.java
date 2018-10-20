package de.hsh.alexander.engine.game;

import javafx.scene.layout.Pane;

import java.util.Observer;

public abstract class Game extends java.util.Observable implements GameInterface {

    private final String name;
    private       Pane   gameContentPane;

    protected Game( Observer o, String name ) {
        this.addObserver( o );
        this.name = name;
        this.gameContentPane = initGameContentWindow();
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
