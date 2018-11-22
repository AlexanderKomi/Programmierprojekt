package common.engine;

import common.MainMenu;
import common.config.WindowConfig;
import common.engine.game.Game;
import common.engine.game.Games;
import common.events.KeyEventManager;
import common.events.MouseEventManager;
import de.hsh.Julian.Leertastenklatsche;
import de.hsh.alexander.PacManController;
import de.hsh.amir.AmirsGame;
import de.hsh.daniel.RAM;
import de.hsh.dennis.DennisGame;
import de.hsh.kevin.controller.TIController;
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

    private static KeyEventManager   keyEventManager;
    private static MouseEventManager mouseEventManager;
    private        Stage             stage;
    private        Scene             scene;
    private        Games             games = new Games(); // Tracks all the games
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
        keyEventManager = new KeyEventManager();
        mouseEventManager = new MouseEventManager();

        this.setGames( createGames( this ) );

        this.setMenu( configMainMenu( this, games.getNames() ) );
        this.getMenu().addObserver( this );
        this.getMenu().setGameNames( this.games );
        this.getMenu().initGameNames();

        this.scene = new Scene( this.getMenu().vbox );

        addKeyListeners( keyEventManager );
        addMouseEvents( mouseEventManager );

        this.games.addKeyEventManager( keyEventManager );
        this.games.addMouseEventManager( mouseEventManager );
        this.stage.setScene( this.scene );
        this.startEngine();
        this.showWindow();
    }

    public abstract Games createGames( Observer container );

    private void initStage( Stage primaryStage ) {
        this.stage = primaryStage; // This line is required, for reference change.
        this.stage.setTitle(WindowConfig.mainGui_title);
        this.stage.setResizable( false );
        this.stage.setOnCloseRequest( close -> {
            this.stopContainer();
        } );
    }

    private void addKeyListeners( KeyEventManager keyEventManager ) {
        this.scene.setOnKeyPressed( keyEventManager::keyPressed );
        this.scene.setOnKeyReleased( keyEventManager::keyReleased );
        this.scene.setOnKeyTyped( keyEventManager::keyTyped );
    }

    private void addMouseEvents( MouseEventManager mouseEventManager ) {
        this.scene.setOnMouseClicked( mouseEventManager::mouseClicked );
        this.scene.setOnMouseDragged( mouseEventManager::mouseDragged );
        this.scene.setOnMouseReleased( mouseEventManager::mouseReleased );
    }

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
        Platform.exit();
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

    public boolean containsGame( Game game ) {
        return this.getGames().contains( game );
    }

    public boolean containsGame( String gameName ) {
        return this.getGames().contains( gameName );
    }

    public void setGameShown( Game g ) {
        Pane p = g.getGameContentPane();
        if ( p != null ) {
            this.stage.getScene().rootProperty().setValue( p );
            changeTitle(g);
        }
        else {
            throw new NullPointerException( "Pane is null" );
        }
    }

    private void changeTitle(Game g) {
        if ( g instanceof PacManController) {
            this.stage.setTitle(WindowConfig.alexander_title);
        }
        else if ( g instanceof AmirsGame) {
            this.stage.setTitle(WindowConfig.amir_title);
        }
        else if ( g instanceof RAM) {
            this.stage.setTitle(WindowConfig.daniel_title);
        }
        else if ( g instanceof DennisGame) {
            this.stage.setTitle(WindowConfig.dennis_title);
        }
        else if ( g instanceof Leertastenklatsche) {
            this.stage.setTitle(WindowConfig.julian_title);
        }
        else if ( g instanceof TIController) {
            this.stage.setTitle(WindowConfig.kevin_title);
        }
        else {
            this.stage.setTitle("ERROR SETTING TITLE");
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