package de.hsh.alexander.engine.game;

import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainMenu extends Observable implements Initializable {

    public ArrayList<String> gameNames = new ArrayList<>();
    public Pane              pane      = new Pane();

    public MainMenu() {

    }

    public MainMenu( Observer sceneController, ArrayList<String> games ) {
        this.setGameNames( games );
        this.addObserver( sceneController );
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

    }


    @Override
    public void notifyObservers( Object arg ) {
        this.setChanged();
        super.notifyObservers( arg );
    }

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public void setGameNames( ArrayList<String> gameNames ) {
        this.gameNames = gameNames;
    }

    public void setGameNames( Games games ) {
        this.gameNames = games.stream().map( Game::getName ).collect( Collectors.toCollection( ArrayList::new ) );
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane( Pane p ) {
        this.pane = p;
    }
}
