package de.hsh.alexander.engine.game;

import javafx.scene.layout.Pane;

public abstract class MainMenu {

    private Pane pane;

    public MainMenu() {
        this.pane = initScene();
    }

    protected abstract Pane initScene();

    public MainMenu( Pane pane ) {
        this.pane = pane;
    }

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public Pane getPane() {
        return pane;
    }

    public void setPane( Pane pane ) {
        this.pane = pane;
    }
}
