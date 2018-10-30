package de.hsh.alexander.engine.game;

import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

public abstract class GameMenu extends Observable implements Initializable {

    private Pane menuPane;

    public GameMenu( Observer observer ) {
        this.addObserver( observer );
        this.menuPane = initMenuPane();
    }

    protected abstract Pane initMenuPane();

    public Pane getMenuPane() {
        return menuPane;
    }

    public void setMenuPane( Pane menuPane ) {
        this.menuPane = menuPane;
    }
}
