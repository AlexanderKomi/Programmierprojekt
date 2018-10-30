package de.hsh.alexander.engine.game;

import de.hsh.alexander.util.Logger;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public abstract class Menu extends Observable {

    protected ArrayList<String> gameNames = new ArrayList<>();
    private   Pane              pane;

    public Menu( Observer sceneController, Game[] games ) {
        for ( Game g : games ) {
            gameNames.add( g.getName() );
        }
        this.pane = initScene();
        this.addObserver( sceneController );
    }

    /**
     * @return Content of this menu, when it should be shown.
     */
    protected abstract Pane initScene();

    public void logChildren() {
        if ( this.pane.getChildren().isEmpty() ) {
            Logger.log( super.toString() + " is empty." );
        }
        else {
            pane.getChildren().forEach( Logger::log );
        }
    }

    @Override
    public void notifyObservers( Object arg ) {
        super.notifyObservers( arg );
    }

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public Pane getPane() {
        return pane;
    }

    public void setPane( Pane p ) {
        this.pane = p;
    }
}
