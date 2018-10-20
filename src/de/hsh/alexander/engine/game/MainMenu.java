package de.hsh.alexander.engine.game;

import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

public abstract class MainMenu extends Observable {

    private Pane pane;

    public MainMenu( Observer sceneController, Game[] games ) {
        this.pane = initScene( games );
        this.addObserver( sceneController );
    }

    /**
     * @param games
     *         The collection of all games. Needed for reference, when selecting a game.
     *
     * @return Pane Window content of this menu, when it should be shown.
     */
    protected abstract Pane initScene( Game[] games );

    @Override
    public void notifyObservers( Object arg ) {
        super.notifyObservers( arg );
    }

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public Pane getPane() {
        return pane;
    }
}
