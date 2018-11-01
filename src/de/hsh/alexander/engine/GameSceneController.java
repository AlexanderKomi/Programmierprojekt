package de.hsh.alexander.engine;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.MainMenu;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameSceneController {

    private Scene    scene;
    private MainMenu menu = new MainMenu() {
        @Override
        protected Pane initScene() {
            return new Pane();
        }

        @Override
        public void initialize( URL location, ResourceBundle resources ) {
            this.setPane( new Pane() );
        }
    };

    void showMainMenu() {
        this.scene.rootProperty().setValue( this.menu.getPane() );
    }

    void showGame( Game game ) {
        Pane p = game.getGameContentPane();
        if ( p != null ) {
            this.scene.rootProperty().setValue( p );
        }
        else {
            throw new NullPointerException( "Pane is null" );
        }
    }


    //-------------------------------------- GETTER & SETTER --------------------------------------

    MainMenu getMenu() {
        return menu;
    }

    protected void setMenu( MainMenu menu ) {
        this.menu = menu;
    }

    Scene getScene() {
        return scene;
    }

    protected void setScene( Scene scene ) {
        this.scene = scene;
    }

}
