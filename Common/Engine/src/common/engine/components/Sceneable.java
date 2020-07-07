package common.engine.components;

import javafx.scene.Scene;

import java.util.Observable;
import java.util.Observer;

public abstract class Sceneable extends Observable implements Observer {

    private Scene scene;

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

    public void setScene(Scene scene) {
        if (scene == null) {
            throw new NullPointerException("Scene is null.");
        }
        this.scene = scene;
    }
}
