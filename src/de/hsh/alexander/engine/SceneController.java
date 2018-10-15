package de.hsh.alexander.engine;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.MainMenu;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SceneController {

    private Scene      scene;
    private MainMenu   mainMenu;
    private List<Game> games = new ArrayList<Game>(); // Tracks all the games

    void configMainMenu( MainMenu mainMenu ) {
        this.mainMenu = mainMenu;
        this.scene = new Scene( this.mainMenu.getPane() );
    }


    public void addGames( Game... games ) {
        this.games = Arrays.asList( games );
    }

    // TODO : Write a function for switching scenes, which can be called by a game and mainmenu. Use Observer Pattern.

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public Scene getScene() {
        return scene;
    }

    public void setScene( Scene scene ) {
        this.scene = scene;
    }

}
