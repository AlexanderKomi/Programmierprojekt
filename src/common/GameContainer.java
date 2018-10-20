package common;

import de.hsh.alexander.engine.FXGameContainer;
import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.MainMenu;
import de.hsh.alexander.game.PacManCoop;
import de.hsh.alexander.util.Logger;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class GameContainer extends FXGameContainer {

    public static final double window_width  = 400.0;
    public static final double window_height = 400.0;


    @Override
    protected Stage configWindow( Stage primaryStage ) {
        primaryStage.setX( window_width );
        primaryStage.setY( window_height );
        primaryStage.setResizable( false );
        return primaryStage;
    }

    @Override
    protected MainMenu configMainMenu( Observer sceneController, Game[] games ) {
        return new common.MainMenu( sceneController, games );
    }

    @Override
    public Game[] addGames( Observer sceneController ) {
        return new Game[] {
                new PacManCoop( sceneController )
        };
    }

    @Override
    public void update( Game game, Object arg ) {
        Logger.log( game, arg );
        if ( game instanceof PacManCoop ) {
            this.getStage().setTitle( "PacMan Coop" );
        }
    }

    @Override
    public void update( MainMenu mainMenu, Object arg ) {
        Logger.log( "MainMenu : " + arg.toString() );
    }

    @Override
    public void update( Observable o, Object arg ) {
        Logger.log( o, arg );
    }

    @Override
    public void startContainer( String[] args ) {
        launch( args );
    }

    @Override
    public void startContainer() {
        launch();
    }

    @Override
    public void render() {
        //Logger.log("Rendering");
    }
}
