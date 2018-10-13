package de.hsh.alexander.examples;

import de.hsh.alexander.engine.FXGameContainer;
import de.hsh.alexander.engine.game.MainMenu;
import javafx.stage.Stage;

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
    protected MainMenu configMainMenu() {
        return new ExampleMainMenu();
    }


    // ----------------------------------- GETTER & SETTER  -----------------------------------

}
