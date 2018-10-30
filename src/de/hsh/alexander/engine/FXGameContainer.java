package de.hsh.alexander.engine;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.GameMenu;
import de.hsh.alexander.engine.game.Games;
import de.hsh.alexander.engine.game.Menu;
import javafx.application.Platform;
import javafx.scene.Scene;
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
        extends Container implements Observer {

    private Stage               stage;
    private GameSceneController gameSceneController;
    private Games               games = new Games(); // Tracks all the games

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
        Container.setLaunched( true );
        this.initStage( primaryStage );
        initSceneController();
        this.stage.setScene( this.gameSceneController.getScene() );
        this.startEngine();
        this.showWindow();
    }

    private void initSceneController() {
        Games games = createGames( this );
        this.gameSceneController = new GameSceneController();
        this.gameSceneController.setMenu( configMainMenu( this, games ) );
        this.gameSceneController.setScene( new Scene( this.gameSceneController.getMenu().getPane() ) );
    }
    //Engine properties

    public abstract Games createGames( Observer container );

    private void initStage( Stage primaryStage ) {
        this.stage = primaryStage; // This line is required, for reference change.
        this.stage = configWindow( this.stage );
        this.stage.setOnCloseRequest( close -> {
            this.stopContainer();
            Platform.exit();
        } );
    }

    protected abstract Menu configMainMenu( Observer container, Games games );


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

    protected void showMainMenu() {
        this.gameSceneController.showMainMenu();
    }

    public GameSceneController getGameSceneController() {
        return gameSceneController;
    }

    public abstract void update( Game game, Object arg );

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

    public abstract void update( Menu menu, Object arg );

    public abstract void update( GameMenu gameMenu, Object arg );

    protected void setGameShown( Game g ) {
        Pane p = g.getGameContentPane();
        if ( p != null ) {
            this.gameSceneController.getScene().rootProperty().setValue( p );
        }
        else {
            throw new NullPointerException( "Pane is null" );
        }
    }

    protected Stage getStage() {
        return this.stage;
    }

    protected void setMainMenuShown( Menu m ) {
        Pane pane = m.getPane();
        if ( pane != null ) {
            this.gameSceneController.getScene().rootProperty().setValue( pane );
        }
        else {
            throw new NullPointerException( "Pane is null" );
        }
    }

    protected void setGameShown( int index ) {
        this.gameSceneController.showGame( this.getGames().get( index ) );
    }

    public Games getGames() {
        return games;
    }

    protected void setGames( Games games ) {
        this.games = games;
    }

}
