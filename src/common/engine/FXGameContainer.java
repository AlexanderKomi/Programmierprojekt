package common.engine;

import common.MainMenu;
import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.engine.components.game.GameEntryPoints;
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
 * FXGameContainer is a container for a game engine, a main menu and all gameEntryPoints.
 *
 * @author Alexander Komischke
 */
public abstract class FXGameContainer extends Container implements Observer {

    private static KeyEventManager   keyEventManager;
    private static MouseEventManager mouseEventManager;
    private        Stage             stage;
    private        GameEntryPoints   gameEntryPoints = new GameEntryPoints(); // Tracks all the gameEntryPoints
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

        this.setGameEntryPoints( createGames( this ) );

        this.setMenu( configMainMenu( getGameEntryPoints().getNames() ) );
        this.getMenu().addObserver( this );
        this.getMenu().initGameNames();

        this.stage.setScene(this.getMenu().getScene());
        //keyEventManager.addKeyEvents( this.getMenu().getScene() );
        //mouseEventManager.addKeyEvents( this.getMenu().getScene() );

        this.gameEntryPoints.addKeyEventManager( keyEventManager );
        this.gameEntryPoints.addMouseEventManager( mouseEventManager );
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

    public abstract GameEntryPoints createGames( Observer o );

    public GameEntryPoints getGameEntryPoints() {
        return gameEntryPoints;
    }


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
        stage.setTitle(WindowConfig.mainGui_title);
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

    protected void setGameEntryPoints( GameEntryPoints gameEntryPoints ) {
        if ( gameEntryPoints == null ) {
            throw new IllegalArgumentException( "GameEntryPoints are null" );
        }
        else {
            this.gameEntryPoints = gameEntryPoints;
        }
    }

    public boolean containsGame( GameEntryPoint gameEntryPoint ) {
        return this.getGameEntryPoints().contains( gameEntryPoint );
    }

    public static KeyEventManager getKeyEventManager() {
        return keyEventManager;
    }

    public boolean containsGame( String gameName ) {
        return this.getGameEntryPoints().contains( gameName );
    }

    protected void setGameShown( int index ) {
        this.setGameShown( this.getGameEntryPoints().get( index ) );
    }

    public void setGameShown(String gameName) {
        GameEntryPoint g = this.getGameEntryPoints().get( gameName );
        if (g == null) {
            throw new IllegalArgumentException( "GameEntryPoint not found" );
        }
        else {
            setGameShown(g);
        }
    }

    public void setGameShown( GameEntryPoint gameEntryPoint ) {
        Scene s = gameEntryPoint.getScene();
        stage.setTitle(gameEntryPoint.getName());
        Logger.log( this.getClass() + ": GameEntryPoint scene : " + s );
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
