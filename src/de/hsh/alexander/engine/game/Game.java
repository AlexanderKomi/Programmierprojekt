package de.hsh.alexander.engine.game;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.Observer;

public abstract class Game extends java.util.Observable implements GameInterface {

    private GameMenu menu;
    private Pane     gameContentPane;
    private Observer observer;

    protected Game( Observer o ) {
        this.observer = o;
    }

    public void init() {
        this.menu = initMenu();
        this.gameContentPane = initGameContentWindow();
        this.gameContentPane.getChildren().addAll( createGameContent() );
    }


    public void notifyObservers() {
        this.observer.update( this, gameContentPane );
    }

    protected abstract Node createGameContent();

    // ----------------------------------- GETTER & SETTER  -----------------------------------

    public Pane getGameContentPane() {
        return gameContentPane;
    }

    public void setGameContentPane( Pane gameContentPane ) {
        this.gameContentPane = gameContentPane;
    }

    public GameMenu getMenu() {
        return menu;
    }

    public void setMenu( GameMenu menu ) {
        this.menu = menu;
    }


}
