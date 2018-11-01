package de.hsh.alexander.engine.game;

import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

public abstract class GameMenu extends Observable implements Initializable {

    private Pane menuPane;

    public GameMenu() {}

    public GameMenu( Observer observer ) {
        this.menuPane = initMenuPane();
        this.addObserver( observer );
    }

    @Override
    public void notifyObservers() {
        this.setChanged();
        super.notifyObservers();
    }

    @Override
    public void notifyObservers( Object arg ) {
        this.setChanged();
        super.notifyObservers( arg );
    }

    protected abstract Pane initMenuPane();

    public Pane getMenuPane() {
        return menuPane;
    }

    public void setMenuPane( Pane menuPane ) {
        this.menuPane = menuPane;
    }
}
