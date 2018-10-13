package de.hsh.alexander.engine.game;

import javafx.scene.layout.Pane;

public interface GameInterface {

    /**
     * Initializes the game menu
     */
    GameMenu initMenu();

    Pane initGameContentWindow();

}
