package de.hsh.Examples;

import de.hsh.alexander.engine.game.GameMenu;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ExampleGameMenu extends GameMenu {

    public void ExampleGameMenu(){

    }

    @Override
    protected Pane initMenuPane() {
        return new Pane();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
