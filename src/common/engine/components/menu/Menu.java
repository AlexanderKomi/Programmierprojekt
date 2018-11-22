package common.engine.components.menu;

import common.engine.components.Sceneable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.Observer;

public abstract class Menu extends Sceneable {

    private Pane menuPane;

    public Menu() {
    }

    public Menu(Observer observer) {
        this.addObserver(observer);
    }

    public Pane getMenuPane() {
        return menuPane;
    }

    public void setMenuPane(Pane menuPane) {
        if (menuPane == null) {
            throw new IllegalArgumentException("menuPane is null");
        }
        this.menuPane = menuPane;
        if (this.getScene() != null) {
            this.getScene().setRoot(this.menuPane);
        } else {
            this.setScene(new Scene(this.menuPane));
        }
    }

}
