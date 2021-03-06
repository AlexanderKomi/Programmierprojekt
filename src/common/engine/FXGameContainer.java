package common.engine;

import common.MainMenu;
import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.engine.components.game.GameEntryPoints;
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

    private        Stage           stage;
    private static GameEntryPoints gameEntryPoints = new GameEntryPoints(); // Tracks all the gameEntryPoints
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

        this.setGameEntryPoints( createGames( this ) );

        this.setMenu( configMainMenu( getGameEntryPoints().getNames() ) );
        this.getMenu().addObserver( this );
        this.getMenu().initGameNames();

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
        beforeStoppingContainer();
        this.setRunning( false );
        this.getEngine().shutdown();
        Platform.exit();
    }

    protected abstract void beforeStoppingContainer();

    private Stage getStage() {
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
            FXGameContainer.gameEntryPoints = gameEntryPoints;
        }
    }

    public boolean containsGame( GameEntryPoint gameEntryPoint ) {
        return this.getGameEntryPoints().contains( gameEntryPoint );
    }

    public boolean containsGame( String gameName ) {
        return this.getGameEntryPoints().contains( gameName );
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

    protected void setGameShown( GameEntryPoint gameEntryPoint ) {
        Scene s = gameEntryPoint.getScene();
        stage.setTitle(gameEntryPoint.getName());
        if (s != null) {
            if (s.rootProperty().get() != null) {
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
}
