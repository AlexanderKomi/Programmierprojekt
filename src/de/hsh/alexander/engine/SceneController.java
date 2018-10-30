package de.hsh.alexander.engine;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.Menu;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class SceneController {

    private Scene scene;
    private Menu  menu;


    void showMainMenu() {
        this.scene.rootProperty().setValue( this.menu.getPane() );
    }

    void showGame( Game game ) {
        Pane p    = game.getGameContentPane();
        if ( p != null ) {
            this.scene.rootProperty().setValue( p );
        }
        else {
            throw new NullPointerException( "Pane is null" );
        }
    }


    //-------------------------------------- GETTER & SETTER --------------------------------------


    protected void setMenu( Menu menu ) {
        this.menu = menu;
    }
    Menu getMenu() {
        return menu;
    }

    protected void setScene( Scene scene ) {
        this.scene = scene;
    }

    Scene getScene() {
        return scene;
    }

}
