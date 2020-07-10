package common.engine;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * FXGameContainer is a container for a game engine, a main menu and all gameEntryPoints.
 *
 * @author Alexander Komischke
 */
public abstract class FXGameContainer extends EngineGameContainer {

    private        Stage           stage;
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
        this.setLaunched();
        this.initStage( primaryStage );

        FXGameContainer.gameEntryPoints = createGames( this );

        this.menu = configMainMenu(gameEntryPoints.getNames() );
        this.menu.addObserver( this );
        this.menu.initGameNames();

        this.stage.setScene(this.menu.getScene());
        this.engine.start();
        this.showWindow();
    }

    private void initStage( Stage primaryStage ) {
        this.stage = primaryStage; // This line is required, for reference change.
        this.stage.setTitle(WindowConfig.mainGui_title);
        this.stage.setResizable( false );
        this.stage.setOnCloseRequest(close -> this.stopContainer());
    }

    private void showWindow() {
        if (this.stage == null) {
            throw new NullPointerException( "Stage is not existing anymore." );
        }
        this.stage.show();
    }

    public void showMainMenu() {
        this.stage.setScene(this.menu.getScene());
        stage.setTitle(WindowConfig.mainGui_title);
    }

    public void setGameShown(final String gameName) {
        setGameShown(gameEntryPoints.get(gameName ));
    }

    protected void setGameShown( GameEntryPoint gameEntryPoint ) {
        stage.setTitle(gameEntryPoint.getName());
        Scene s = gameEntryPoint.getScene();
        if (s == null) {
            throw new NullPointerException("Scene is null");
        }
        if (s.rootProperty().get() == null) {
            throw new NullPointerException("Scene root property is null.");
        }
        stage.setScene(s);
    }
    /**
     * Stops the Container instance and the running engine.
     *
     * @see GameContainerInterface
     */
    @Override
    public void stopContainer() {
        beforeStoppingContainer();
        this.engine.shutdown();
        Platform.exit();
    }


    protected abstract common.MainMenu configMainMenu(ArrayList<String> games);

}
