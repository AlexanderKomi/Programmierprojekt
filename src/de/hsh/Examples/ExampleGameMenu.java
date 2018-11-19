package de.hsh.Examples;

import de.hsh.alexander.engine.game.GameMenu;
import javafx.scene.layout.Pane;

public class ExampleGameMenu extends GameMenu {

    public void ExampleGameMenu(){

    }

    @Override
    protected Pane initMenuPane() {
        return new Pane();
    }

}
