package de.hsh.alexander.engine;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.Menu;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class SceneController {

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

    void showMainMenu() {
        this.menu.logChildren();
        this.scene.rootProperty().setValue( this.menu.getPane() );
    }

    void showGame( int index ) {
        Game game = this.getGames()[ index ];
        Pane p    = game.getGameContentPane();
        if ( p != null ) {
            this.menu.logChildren();
            this.scene.rootProperty().setValue( p );
        }
        else {
            throw new NullPointerException( "Pane is null" );
        }
    }

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public Game[] getGames() {
        return games;
    }

    Menu getMenu() {
        return menu;
    }

    Scene getScene() {
        return scene;
    }

}
