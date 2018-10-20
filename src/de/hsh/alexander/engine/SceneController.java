package de.hsh.alexander.engine;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.Menu;
import javafx.scene.Scene;

class SceneController {

    private Scene  scene;
    private Menu   menu;
    private Game[] games; // Tracks all the games

    void configMainMenu( Menu menu ) {
        this.menu = menu;
        this.scene = new Scene( this.menu.getPane() );
    }

    void addGames( Game... games ) {
        this.games = games;
    }

    //-------------------------------------- GETTER & SETTER --------------------------------------


    Menu getMenu() {
        return menu;
    }

    Scene getScene() {
        return scene;
    }

    Game[] getGames() {
        return games;
    }

}
