package de.hsh.alexander.engine.game;

import de.hsh.alexander.util.Logger;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public abstract class MainMenu extends Observable implements Initializable {

    protected ArrayList<String> gameNames = new ArrayList<>();
    private   Pane              pane      = new Pane();

    public MainMenu() {

    }

    public MainMenu( Observer sceneController, ArrayList<String> games ) {
        this.setGameNames( games );
        this.pane = initScene();
        this.addObserver( sceneController );
    }

    public void setGameNames( ArrayList<String> gameNames ) {
        this.gameNames = gameNames;
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
            pane.getChildren().stream().parallel().map( Logger::log );
        }
    }

    //-------------------------------------- GETTER & SETTER --------------------------------------

    @Override
    public void notifyObservers( Object arg ) {
        this.setChanged();
        super.notifyObservers( arg );
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane( Pane p ) {
        this.pane = p;
    }

    public void setGameNames( Games games ) {
        this.gameNames = new ArrayList<>();
        games.forEach( game -> {
            this.gameNames.add( game.getName() );
        } );
    }
}
