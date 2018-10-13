package de.hsh.alexander.engine;

import de.hsh.alexander.engine.game.GameInterface;
import de.hsh.alexander.engine.game.MainMenu;
import javafx.scene.Scene;

import java.util.ArrayList;

public class SceneController {

    private Scene                    scene;
    private MainMenu                 mainMenu;
    private ArrayList<GameInterface> games = new ArrayList<>(); // Tracks all the games

    void init( MainMenu mainMenu ) {
        this.mainMenu = mainMenu;
        this.scene = new Scene( this.mainMenu.getPane() );
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
