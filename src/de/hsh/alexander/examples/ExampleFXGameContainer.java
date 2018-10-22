package de.hsh.alexander.examples;

import de.hsh.alexander.engine.FXGameContainer;
import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.Menu;
import de.hsh.alexander.util.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Alexander Komischke
 */
public class ExampleFXGameContainer extends FXGameContainer {

    /**
     * Needed for JavaFX. Do not remove.
     *
     * @author Alexander Komischke
     */
    @Override
    public void startContainer( String[] args ) { launch( args ); }

    /**
     * @author Alexander Komischke
     */
    @Override
    public void startContainer() {
        launch();
    }

    /**
     * Gets called every frame from engine.
     *
     * @author Alexander Komischke
     */
    @Override
    public void render() {
        //Logger.log("Rendering...");
    }

    /**
     * @author Alexander Komischke
     */
    @Override
    public Game[] addGames( Observer sceneController ) {
        ExampleGame e = new ExampleGame( sceneController );
        return new Game[] { e };
    }

    /**
     * @author Alexander Komischke
     */
    @Override
    protected Menu configMainMenu( Observer sceneController, Game[] games ) {
        return new ExampleMenu( sceneController, games );
    }

    //----------- Update

    /**
     * @author Alexander Komischke
     */
    @Override
    protected Stage configWindow( Stage primaryStage ) {
        primaryStage.setTitle( "Example" );
        primaryStage.setResizable( false );
        return primaryStage;
    }

    /**
     * @author Alexander Komischke
     */
    @Override
    public void update( Game game, Object arg ) {
        if ( arg instanceof MouseEvent ) {
            MouseEvent m = (MouseEvent) arg;
            Logger.log( "Clicked on : (" + m.getSceneX() + "," + m.getSceneY() + ")" );
            this.showMainMenu();
        }
        else {
            Logger.log( game.toString() + "\t\t" + arg.toString() );
        }
    }

    /**
     * @author Alexander Komischke
     */
    @Override
    public void update( Observable o, Object arg ) {
    }

    /**
     * @author Alexander Komischke
     */
    @Override
    public void update( Menu menu, Object arg ) {
        if ( arg instanceof String ) { // Send a nice message, if you are kind enough :)
            String message = (String) arg;
            Logger.log( menu.toString() + "\t\t" + message );
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
            Logger.log( menu.toString() + "\t\t" + arg );
        }

    }


    // ----------------------------------- GETTER & SETTER  -----------------------------------

}
