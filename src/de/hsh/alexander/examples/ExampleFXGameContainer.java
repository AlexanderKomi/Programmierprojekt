package de.hsh.alexander.examples;

import de.hsh.alexander.engine.FXGameContainer;
import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.MainMenu;
import de.hsh.alexander.util.Logger;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ExampleFXGameContainer extends FXGameContainer {

    @Override
    public void startContainer( String[] args ) { launch( args ); }

    @Override
    public void startContainer() {
        launch();
    }


    /**
     * Updates every frame drawn.
     */
    @Override
    public void render() {
        //Logger.log("Rendering...");
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
        e.init();
        return new Game[] { e };
    }

    @Override
    public void onUpdate( Observable o, Object arg ) {
        Logger.log( o.toString() + "\t\t" + arg.toString() );
        List<Game> games = this.getSceneController().getGames();
        if ( games != null ) {
            Pane p = games.get( 0 ).getGameContentPane();
            if ( p != null ) {
                this.getSceneController().getScene().rootProperty().setValue( p );
            }
            else {
                throw new NullPointerException( "Parent is null" );
            }
        }
        else {
            throw new NullPointerException( "Games is null" );
        }

    }


    // ----------------------------------- GETTER & SETTER  -----------------------------------

}
