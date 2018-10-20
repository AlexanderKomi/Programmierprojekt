package de.hsh.alexander.engine.game;

import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

public abstract class MainMenu extends Observable {

    private Pane     pane;
    private Observer sceneController;

    public MainMenu( Observer sceneController, Game[] games ) {
        this.sceneController = sceneController;
        this.addObserver( this.sceneController );
        this.pane = initScene( games );
    }

    /**
     * @param games
     *         The collection of all games. Needed for reference, when selecting a game.
     *
     * @return Pane Window content of this menu, when it should be shown.
     */
    protected abstract Pane initScene( Game[] games );

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public Pane getPane() {
        return pane;
    }

    protected Observer getSceneController() {
        return sceneController;
    }
}
