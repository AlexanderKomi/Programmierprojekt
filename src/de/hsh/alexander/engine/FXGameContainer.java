package de.hsh.alexander.engine;

import de.hsh.alexander.game.Game;
import de.hsh.alexander.game.GameMenu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;


public abstract class FXGameContainer
        extends Application
        implements GameContainerInterface {


    // JavaFX Properties
    private static boolean fxApplicationLaunched = false;
    private        Canvas  canvas;
    private        Stage   stage;

    //Engine properties
    private Java2DEngine engine;
    private boolean      running = false;

    // Games
    private ArrayList<Game> games = new ArrayList<>(); // Tracks all the games
    private GameMenu        mainScreen;

    public FXGameContainer() {
        initialize();
    }

    public FXGameContainer( Game... games ) {
        this.games.addAll( Arrays.asList( this.games.toArray( games ) ) );
        initialize();
    }

    public FXGameContainer( ArrayList<Game> games ) {
        this.games = games;
        initialize();
    }

    public static boolean launched() {
        return FXGameContainer.fxApplicationLaunched;
    }

    /**
     * Gets called after every constructor
     */
    private void initialize() {
        this.setEngine( new Java2DEngine() );
        this.getEngine().setGameContainer( this ); // This must be in every class, which is an FXGameContainer.
    }

    /**
     * @param primaryStage
     *         Main window used.
     */
    @Override
    public void start( Stage primaryStage ) {
        if ( launched() ) {
            throw new IllegalStateException( "Already launched an JavaFX Application. Use existing Stage instead." );
        }

        FXGameContainer.fxApplicationLaunched = true;
        this.setRunning( true );
        this.getEngine().init();
        this.getEngine().start();

        this.stage = primaryStage; // This line is required, for reference change.
        this.stage = configGui( this.stage );

        this.stage.setOnCloseRequest( close -> {
            this.stopContainer();
            Platform.exit();
        } );
        this.stage.show();
    }

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

    /**
     * Configures the whole window. Only call once...
     *
     * @param primaryStage
     *         Window which is created, when calling launch. Needs to be modified.
     *
     * @return Returns the modified version of the parameter.
     */
    protected abstract Stage configGui( Stage primaryStage );

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized void setRunning( boolean running ) {
        this.running = running;
    }

    public Java2DEngine getEngine() {return this.engine;}

    protected void setEngine( Java2DEngine java2DEngine ) {
        this.engine = java2DEngine;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas( Canvas canvas ) {
        this.canvas = canvas;
    }

    public Stage getStage() {
        return this.stage;
    }
}
