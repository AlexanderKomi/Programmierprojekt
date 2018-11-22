package common.engine;

import common.MainMenu;
import common.config.WindowConfig;
import common.engine.components.game.Game;
import common.engine.components.game.Games;
import common.events.KeyEventManager;
import common.events.MouseEventManager;
import common.util.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * FXGameContainer is a container for a game engine, a main menu and all games.
 *
 * @author Alexander Komischke
 */
public abstract class FXGameContainer extends Container implements Observer {

    private static KeyEventManager   keyEventManager;
    private static MouseEventManager mouseEventManager;
    private        Stage             stage;
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

        this.setGames(createGames(this));

        this.setMenu(configMainMenu(getGames().getNames()));
        this.getMenu().addObserver( this );
        this.getMenu().initGameNames();

        this.stage.setScene(this.getMenu().getScene());
        //keyEventManager.addKeyEvents( this.getMenu().getScene() );
        //mouseEventManager.addKeyEvents( this.getMenu().getScene() );

        this.games.addKeyEventManager( keyEventManager );
        this.games.addMouseEventManager( mouseEventManager );
        this.stage.setScene(this.getMenu().getScene());
        this.startEngine();
        this.showWindow();
    }

    private void initStage( Stage primaryStage ) {
        this.stage = primaryStage; // This line is required, for reference change.
        this.stage.setTitle(WindowConfig.mainGui_title);
        this.stage.setResizable( false );
        this.stage.setOnCloseRequest(close -> this.stopContainer());
    }

    protected void setGames(Games games) {
        if (games == null) {
            throw new IllegalArgumentException("Games are null");
        } else {
            this.games = games;
        }
    }

    public abstract Games createGames(Observer o);


    private void showWindow() {
        if ( this.stage != null ) {
            this.stage.show();
        }
        else {
            throw new NullPointerException( "Stage is not existing anymore." );
        }
    }

    public void showMainMenu() {
        this.stage.setScene(this.getMenu().getScene());
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

    protected abstract common.MainMenu configMainMenu(ArrayList<String> games);

    public boolean containsGame( Game game ) {
        return this.getGames().contains( game );
    }

    public boolean containsGame( String gameName ) {
        return this.getGames().contains( gameName );
    }

    public static KeyEventManager getKeyEventManager() {
        return keyEventManager;
    }

    protected void setGameShown( int index ) {
        this.setGameShown( this.getGames().get( index ) );
    }

    public Games getGames() {
        return games;
    }

    public void setGameShown(String gameName) {
        Game g = this.getGames().get(gameName);
        if (g == null) {
            throw new IllegalArgumentException("Game not found");
        }
        else {
            setGameShown(g);
        }
    }

    public void setGameShown(Game game) {
        Scene s = game.getScene();
        Logger.log(this.getClass() + ": Game scene : " + s);
        if (s != null) {
            if (s.rootProperty().get() != null) {
                Logger.log(this.getClass() + ": Draw Scene");

                try {
                    getStage().setScene(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                throw new NullPointerException("Scene root property is null.");
            }
        } else {
            throw new NullPointerException("Scene is null");
        }
    }

    private void setStage(Stage stage) {
        this.stage = stage;
    }
}
