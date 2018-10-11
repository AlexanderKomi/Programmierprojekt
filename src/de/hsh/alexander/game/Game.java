package de.hsh.alexander.game;

import javafx.scene.Scene;

public abstract class Game implements GameInterface {

    private Scene scene;    // The current scene drawn onto the stage.

    @Override
    public Scene initUI() {
        return this.scene;
    }

    // ----------------------------------- GETTER & SETTER  -----------------------------------

    public Scene getScene() {
        return scene;
    }

    public void setScene( Scene scene ) {
        this.scene = scene;
    }
}
