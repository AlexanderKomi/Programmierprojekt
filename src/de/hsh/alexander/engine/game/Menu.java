package de.hsh.alexander.engine.game;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public abstract class Menu extends Observable {

    private static final ArrayList<String> gameNames = new ArrayList<>();
    private              Pane              pane;

    public Menu( Observer sceneController, Game[] games ) {
        for ( Game game : games ) {
            gameNames.add( game.getName() );
        }
        this.pane = initScene();
        this.addObserver( sceneController );
    }

    /**
     * @return Content of this menu, when it should be shown.
     */
    protected abstract Pane initScene();

    @Override
    public void notifyObservers( Object arg ) {
        super.notifyObservers( arg );
    }

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public Pane getPane() {
        return pane;
    }
}
