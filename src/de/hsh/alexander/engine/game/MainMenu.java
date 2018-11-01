package de.hsh.alexander.engine.game;

import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observer;
import java.util.ResourceBundle;

public class MainMenu extends VBox implements Initializable {

    public ArrayList<String> gameNames = new ArrayList<>();
    public Pane              pane      = new Pane();

    public MainMenu() {

    }

    public MainMenu( Observer sceneController, ArrayList<String> games ) {
        this.setGameNames( games );
        //this.addObserver( sceneController );
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

    }

    public void setGameNames( ArrayList<String> gameNames ) {
        this.gameNames = gameNames;
    }

    //-------------------------------------- GETTER & SETTER --------------------------------------

    /*
    @Override
    public void notifyObservers( Object arg ) {
        this.setChanged();
        super.notifyObservers( arg );
    }
*/
    public Pane getPane() {
        return pane;
    }

    public void setPane( Pane p ) {
        this.pane = p;
    }
}
