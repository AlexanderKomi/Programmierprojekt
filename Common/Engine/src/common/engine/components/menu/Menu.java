package common.engine.components.menu;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

public abstract class Menu extends Observable implements Observer {

    private Scene scene;

    public Menu() {
    }

    @Override
    public void notifyObservers() {
        this.setChanged();
        super.notifyObservers();
    }

    @Override
    public void notifyObservers(Object arg) {
        this.setChanged();
        super.notifyObservers(arg);
    }

    public Scene getScene() {
        return scene;
    }

    public void setMenuPane(Pane menuPane) {
        if (menuPane == null) {
            throw new NullPointerException("menuPane is null");
        }
        if (this.scene != null) {
            this.scene.setRoot(menuPane);
        } else {
            this.scene = new Scene(menuPane);
        }
    }

}
