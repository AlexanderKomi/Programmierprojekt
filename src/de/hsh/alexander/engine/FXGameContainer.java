package de.hsh.alexander.engine;

import de.hsh.alexander.engine.game.MainMenu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;


public abstract class FXGameContainer
        extends Application
        implements GameContainerInterface {


    // JavaFX Properties
    private static boolean         fxApplicationLaunched = false;
    private        Stage           stage;
    //Engine properties
    private        Java2DEngine    engine;
    private        boolean         running               = false;
    private        SceneController sceneController       = new SceneController();

    public FXGameContainer() {
        this.setEngine( new Java2DEngine() );
        this.getEngine().setGameContainer( this ); // This must be in every class, which is an FXGameContainer.
    }

    /**
     * Launches the javafx Thread. When using an FX Container, apply every interaction, only after the FXThread isLaunched.
     *
     * @param primaryStage
     *         Main window used.
     */
    @Override
    public void start( Stage primaryStage ) {
        if ( isLaunched() ) {
            throw new IllegalStateException( "Already isLaunched an JavaFX Application. Use existing Stage instead." );
        }

        this.setRunning( true );
        this.getEngine().init();
        this.getEngine().start();

        initStage( primaryStage );
        FXGameContainer.fxApplicationLaunched = true;
    }

    public static boolean isLaunched() {
        return FXGameContainer.fxApplicationLaunched;
    }

    public Java2DEngine getEngine() {return this.engine;}

    protected void setEngine( Java2DEngine java2DEngine ) {
        this.engine = java2DEngine;
    }

    private void initStage( Stage primaryStage ) {
        this.stage = primaryStage; // This line is required, for reference change.
        this.stage = configWindow( this.stage );
        this.sceneController.init( configMainMenu() );
        this.stage.setScene( this.sceneController.getScene() );
        this.stage.setOnCloseRequest( close -> {
            this.stopContainer();
            Platform.exit();
        } );
        this.showWindow();
    }

    /**
     * Configures the whole window. Only call once...
     *
     * @param primaryStage
     *         Window which is created, when calling launch. Needs to be modified.
     *
     * @return Returns the modified version of the parameter.
     */
    protected abstract Stage configWindow( Stage primaryStage );

    protected abstract MainMenu configMainMenu();

    /**
     * Stops the Container instance and the running engine.
     *
     * @see GameContainerInterface
     */
    @Override
    public void stopContainer() {
        this.setRunning( false );
        this.getEngine().setRunning( false );
    }

    //-------------------------------------- GETTER & SETTER --------------------------------------

    private void showWindow() {
        if ( this.stage != null ) {
            this.stage.show();
        }
        else {
            throw new NullPointerException( "Stage is not existing anymore." );
        }
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized void setRunning( boolean running ) {
        this.running = running;
    }

    public Stage getStage() {
        return this.stage;
    }
}
