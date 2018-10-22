package de.hsh.alexander.engine.game;

import javafx.scene.layout.Pane;

import java.util.Observable;

public abstract class GameMenu extends Observable {

    private Pane menuPane;

    public GameMenu() {
        this.menuPane = initMenuPane();
    }

    protected abstract Pane initMenuPane();

    public Pane getMenuPane() {
        return menuPane;
    }
}
