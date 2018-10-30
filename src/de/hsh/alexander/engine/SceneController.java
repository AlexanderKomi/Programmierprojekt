package de.hsh.alexander.engine;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.Games;
import de.hsh.alexander.engine.game.Menu;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class SceneController {

    private Scene scene;
    private Menu  menu;
    private Games games = new Games(); // Tracks all the games

    SceneController() {}

    /*
    SceneController(Games games, Menu menu){
        this.games = games;
        this.menu = menu;
    }
    */

    void configMainMenu( Menu menu ) {
        this.menu = menu;
        this.scene = new Scene( this.menu.getPane() );
    }

    void addGames( Games games ) {
        this.games.addAll( games );
    }

    void showMainMenu() {
        this.menu.logChildren();
        this.scene.rootProperty().setValue( this.menu.getPane() );
    }

    void showGame( int index ) {
        Game game = this.getGames().get( index );
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

    public Games getGames() {
        return games;
    }

    Menu getMenu() {
        return menu;
    }

    Scene getScene() {
        return scene;
    }

}
