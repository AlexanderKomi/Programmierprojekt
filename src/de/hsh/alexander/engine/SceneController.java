package de.hsh.alexander.engine;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.MainMenu;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SceneController {

    private Scene      scene;
    private MainMenu   mainMenu;
    private List<Game> games = new ArrayList<>(); // Tracks all the games

    void configMainMenu( MainMenu mainMenu ) {
        this.mainMenu = mainMenu;
        this.scene = new Scene( this.mainMenu.getPane() );
    }

    void addGames( Game... games ) {
        this.games = Arrays.asList( games );
    }

    // TODO : Write a function for switching scenes, which can be called by a game and mainmenu. Use Observer Pattern.

    //-------------------------------------- GETTER & SETTER --------------------------------------

    Scene getScene() {
        return scene;
    }

    List<Game> getGames() {
        return games;
    }

}
