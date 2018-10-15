package de.hsh.alexander.examples;

import de.hsh.alexander.engine.FXGameContainer;
import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.MainMenu;
import de.hsh.alexander.util.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class ExampleFXGameContainer extends FXGameContainer {

    @Override
    public void startContainer( String[] args ) { launch( args ); }

    @Override
    public void startContainer() {
        launch();
    }


    @Override
    protected Stage configWindow( Stage primaryStage ) {
        primaryStage.setTitle( "Example" );
        primaryStage.setResizable( false );
        return primaryStage;
    }

    @Override
    protected MainMenu configMainMenu( Observer sceneController ) {
        return new ExampleMainMenu( sceneController );
    }

    @Override
    public Game[] addGames( Observer sceneController ) {
        ExampleGame e = new ExampleGame( sceneController );
        return new Game[] { e };
    }

    //----------- Update

    /**
     * Gets called every frame.
     */
    @Override
    public void render() {
        //Logger.log("Rendering...");
    }

    @Override
    public void onUpdate( Game game, Object arg ) {
        if ( arg instanceof MouseEvent ) {
            MouseEvent m = (MouseEvent) arg;
            Logger.log( "Clicked on : (" + m.getSceneX() + "," + m.getSceneY() + ")" );
            this.showMainMenu();
        }
        else {
            Logger.log( game.toString() + "\t\t" + arg.toString() );
        }
    }

    @Override
    public void onUpdate( MainMenu mainMenu, Object arg ) {
        if ( arg instanceof String ) { // Send a nice message, if you are kind enough :)
            String message = (String) arg;
            Logger.log( mainMenu.toString() + "\t\t" + message );
        }
        else if ( arg instanceof ActionEvent ) { // Any ActionEvent triggered!
            ActionEvent a = (ActionEvent) arg;
            if ( a.getSource() instanceof Button ) {
                Button button = (Button) a.getSource();
                Logger.log( "Button triggered : " + button.getText() );
                Logger.log( String.valueOf( this.getFPS() ) );
                this.setGameShown( 0 ); // Sets game content from list.
            }
        }
        else {
            Logger.log( mainMenu.toString() + "\t\t" + arg );
        }

    }

    /***/
    @Override
    public void onUpdate( Observable o, Object arg ) {
    }


    // ----------------------------------- GETTER & SETTER  -----------------------------------

}
