package de.hsh.alexander.examples;

import de.hsh.alexander.engine.FXGameContainer;
import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.MainMenu;
import de.hsh.alexander.util.Logger;
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
        return new Game[] { new ExampleGame( sceneController ) };
    }

    @Override
    public void onUpdate( Observable o, Object arg ) {
        Logger.log( o.toString() + "\t\t" + arg.toString() );
    }


    // ----------------------------------- GETTER & SETTER  -----------------------------------

}
