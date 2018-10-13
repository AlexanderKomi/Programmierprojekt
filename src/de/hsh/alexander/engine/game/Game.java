package de.hsh.alexander.engine.game;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class Game implements GameInterface {

    private GameMenu menu;
    private Pane     gameContentPane;

    protected Game() {
        this.menu = initMenu();
        this.gameContentPane = initGameContentWindow();
        this.gameContentPane.getChildren().addAll( createGameContent() );
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
