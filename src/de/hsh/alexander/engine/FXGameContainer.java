package de.hsh.alexander.engine;

import common.MainMenu;
import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.Games;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * FXGameContainer is a container for a game engine, a main menu and all games.
 *
 * @author Alexander Komischke
 */
public abstract class FXGameContainer
        extends Container implements Observer {

    private Stage stage;
    private Scene scene;
    private Games games = new Games(); // Tracks all the games
    //Engine properties

    private common.MainMenu menu;

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
        this.setGames( createGames( this ) );
        this.setMenu( configMainMenu( this, games.getNames() ) );
        this.scene = new Scene( this.getMenu().vbox );
        this.stage.setScene( this.scene );
        this.startEngine();
        this.showWindow();
    }

    private void initStage( Stage primaryStage ) {
        this.stage = primaryStage; // This line is required, for reference change.
        //this.stage.setResizable( false );
        this.stage.setOnCloseRequest( close -> {
            this.stopContainer();
            Platform.exit();
        } );
    }

    public abstract Games createGames( Observer container );

    protected abstract common.MainMenu configMainMenu( Observer container, ArrayList<String> games );


    private void showWindow() {
        if ( this.stage != null ) {
            this.stage.show();
        }
        else {
            throw new NullPointerException( "Stage is not existing anymore." );
        }
    }

    public void showMainMenu() {
        this.stage.getScene().rootProperty().setValue( this.getMenu().vbox );
    }

    public MainMenu getMenu() {
        return menu;
    }

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

    public Stage getStage() {
        return this.stage;
    }

    public void setMenu( MainMenu menu ) {
        this.menu = menu;
    }

    public void setGameShown( String gameName ) {
        Game g = this.getGames().get( gameName );
        if ( g == null ) {
            throw new IllegalArgumentException( "Game not found" );
        }
        setGameShown( g );
    }

    public void setGameShown( Game g ) {
        Pane p = g.getGameContentPane();
        if ( p != null ) {
            this.stage.getScene().rootProperty().setValue( p );
        }
        else {
            throw new NullPointerException( "Pane is null" );
        }
    }

    protected void setMainMenuShown( MainMenu m ) {
        if ( m != null ) {
            this.stage.getScene().rootProperty().setValue( m.vbox );
        }
        else {
            throw new NullPointerException( "Pane is null" );
        }
    }

    protected void setGameShown( int index ) {
        this.setGameShown( this.getGames().get( index ) );
    }

    public Games getGames() {
        return games;
    }

    protected void setGames( Games games ) {
        this.games = games;
    }

    void setGameshown( Game game ) {
        Pane p = game.getGameContentPane();
        if ( p != null ) {
            this.stage.getScene().rootProperty().setValue( p );
        }
        else {
            throw new NullPointerException( "Pane is null" );
        }
    }
}
