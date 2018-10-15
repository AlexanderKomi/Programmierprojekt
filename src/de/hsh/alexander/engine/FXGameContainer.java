package de.hsh.alexander.engine;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.MainMenu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

/**
 * FXGameContainer is a container for a game engine, a main menu and all games.
 *
 * @author Alexander Komischke
 */
public abstract class FXGameContainer
        extends Application
        implements GameContainerInterface, Observer {


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

    @Override
    public void update( Observable o, Object arg ) {
        if ( o instanceof Game ) {
            Game g = (Game) o;
            this.onUpdate( g, arg );
        }
        else if ( o instanceof MainMenu ) {
            MainMenu m = (MainMenu) o;
            this.onUpdate( m, arg );
        }
        else {
            this.onUpdate( o, arg );
        }
    }

    public Java2DEngine getEngine() {return this.engine;}

    protected void setEngine( Java2DEngine java2DEngine ) {
        this.engine = java2DEngine;
    }

    private void initStage( Stage primaryStage ) {
        this.stage = primaryStage; // This line is required, for reference change.
        this.stage = configWindow( this.stage );
        this.sceneController.configMainMenu( configMainMenu( this ) );
        this.sceneController.addGames( addGames( this ) );
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

    protected abstract MainMenu configMainMenu( Observer sceneController );

    public abstract Game[] addGames( Observer sceneController );

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

    public abstract void onUpdate( Game game, Object arg );

    public abstract void onUpdate( MainMenu mainMenu, Object arg );

    public static boolean isLaunched() {
        return FXGameContainer.fxApplicationLaunched;
    }

    protected int getFPS() {
        return this.engine.getFps();
    }

    public abstract void onUpdate( Observable o, Object arg );

    protected void setGameShown( Game g ) {
        Pane p = g.getGameContentPane();
        if ( p != null ) {
            this.sceneController.getScene().rootProperty().setValue( p );
        }
        else {
            throw new NullPointerException( "Pane is null" );
        }
    }

    protected void setGameShown( int index ) {
        Game game = this.sceneController.getGames().get( index );
        Pane p    = game.getGameContentPane();
        if ( p != null ) {
            this.sceneController.getScene().rootProperty().setValue( p );
        }
        else {
            throw new NullPointerException( "Pane is null" );
        }
    }

    //-------------------------------------- GETTER & SETTER --------------------------------------

    protected void setMainMenuShown( MainMenu m ) {
        Pane pane = m.getPane();
        if ( pane != null ) {
            this.sceneController.getScene().rootProperty().setValue( pane );
        }
        else {
            throw new NullPointerException( "Pane is null" );
        }
    }

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
}
