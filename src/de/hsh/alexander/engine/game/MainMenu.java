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

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public Pane getPane() {
        return pane;
    }

    protected Observer getSceneController() {
        return sceneController;
    }
}
