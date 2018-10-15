package de.hsh.alexander.engine.game;

import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

public abstract class MainMenu extends Observable {

    private Pane     pane;
    private Observer sceneController;

    public MainMenu( Observer sceneController ) {
        this.sceneController = sceneController;
        this.pane = initScene();
    }

    protected abstract Pane initScene();

    public MainMenu( Observer sceneController, Pane pane ) {
        this.sceneController = sceneController;
        this.pane = pane;
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public Pane getPane() {
        return pane;
    }

    public Observer getSceneController() {
        return sceneController;
    }

    public void setPane( Pane pane ) {
        this.pane = pane;
    }

    public void setSceneController( Observer sceneController ) {
        this.sceneController = sceneController;
    }
}
