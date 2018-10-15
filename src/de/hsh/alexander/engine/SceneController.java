package de.hsh.alexander.engine;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.MainMenu;
import javafx.scene.Scene;

class SceneController {

    private Scene    scene;
    private MainMenu mainMenu;
    private Game[]   games; // Tracks all the games

    void configMainMenu( MainMenu mainMenu ) {
        this.mainMenu = mainMenu;
        this.scene = new Scene( this.mainMenu.getPane() );
    }

    void addGames( Game... games ) {
        this.games = games;
    }

    // TODO : Write a function for switching scenes, which can be called by a game and mainmenu. Use Observer Pattern.

    //-------------------------------------- GETTER & SETTER --------------------------------------


    public MainMenu getMainMenu() {
        return mainMenu;
    }

    Scene getScene() {
        return scene;
    }

    Game[] getGames() {
        return games;
    }

}
