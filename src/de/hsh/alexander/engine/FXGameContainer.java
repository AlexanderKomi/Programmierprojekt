package de.hsh.alexander.engine;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.GameMenu;
import de.hsh.alexander.engine.game.Menu;
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
    private        SceneController sceneController       = new SceneController();
    //Engine properties
    private        Java2DEngine    engine;
    private        boolean         running               = false;

    public FXGameContainer() {
        this.setEngine( new Java2DEngine() );
        this.getEngine().setGameContainer( this ); // This must be in every class, which is an FXGameContainer.
    }

    public Java2DEngine getEngine() {return this.engine;}

    protected void setEngine( Java2DEngine java2DEngine ) {
        this.engine = java2DEngine;
    }

    /**
     * This is not tested !
     *
     * @author Alex
     */
    public FXGameContainer( Java2DEngine engine ) {
        this.setEngine( engine );
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
        FXGameContainer.fxApplicationLaunched = true;
        this.initStage( primaryStage );
        initSceneController();
        this.stage.setScene( this.sceneController.getScene() );
        this.startEngine();
        this.showWindow();

    }

    public static boolean isLaunched() {
        return FXGameContainer.fxApplicationLaunched;
    }

    private void initStage( Stage primaryStage ) {
        this.stage = primaryStage; // This line is required, for reference change.
        this.stage = configWindow( this.stage );
        this.stage.setOnCloseRequest( close -> {
            this.stopContainer();
            Platform.exit();
        } );
    }

    private void initSceneController() {
        this.sceneController.addGames( addGames( this ) );
        this.sceneController.configMainMenu( configMainMenu( this, this.getGames() ) );
    }

    private void startEngine() {
        this.setRunning( true );
        this.getEngine().init();
        this.getEngine().start();
    }

    private void showWindow() {
        if ( this.stage != null ) {
            this.stage.show();
        }
        else {
            throw new NullPointerException( "Stage is not existing anymore." );
        }
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

    public abstract Game[] addGames( Observer sceneController );

    protected abstract Menu configMainMenu( Observer sceneController, Game[] games );

    private Game[] getGames() {
        return this.sceneController.getGames();
    }

    public abstract void update( Game game, Object arg );

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public abstract void update( Observable observable, Object arg );

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

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized void setRunning( boolean running ) {
        this.running = running;
    }

    public abstract void update( Menu menu, Object arg );

    public abstract void update( GameMenu gameMenu, Object arg );

    protected void showMainMenu() {
        this.sceneController.showMainMenu();
    }

    protected int getFPS() {
        return this.engine.getFps();
    }

    protected Stage getStage() {
        return this.stage;
    }

    public SceneController getSceneController() {
        return sceneController;
    }

    protected void setGameShown( Game g ) {
        Pane p = g.getGameContentPane();
        if ( p != null ) {
            this.sceneController.getScene().rootProperty().setValue( p );
        }
        else {
            throw new NullPointerException( "Pane is null" );
        }
    }

    protected void setMainMenuShown( Menu m ) {
        Pane pane = m.getPane();
        if ( pane != null ) {
            this.sceneController.getScene().rootProperty().setValue( pane );
        }
        else {
            throw new NullPointerException( "Pane is null" );
        }
    }

    protected void setGameShown( int index ) {
        this.sceneController.showGame( index );
    }

}
