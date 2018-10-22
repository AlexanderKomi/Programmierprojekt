package common;

import de.hsh.alexander.engine.FXGameContainer;
import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.Menu;
import de.hsh.alexander.game.PacManCoop;
import de.hsh.alexander.util.Logger;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class GameContainer extends FXGameContainer {

    static final double window_width  = 400.0;
    static final double window_height = 400.0;

    @Override
    protected Stage configWindow( Stage primaryStage ) {
        primaryStage.setX( window_width );
        primaryStage.setY( window_height );
        primaryStage.setResizable( false );
        return primaryStage;
    }

    @Override
    protected Menu configMainMenu( Observer sceneController, Game[] games ) {
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

    /**
     * Cast observables to the correct type, and call the correct method.<br>
     * Why?<br>
     * When "notifyObservers()" is called, the argument is always casted to observable.<br>
     * This is why this method is necessary.<br>
     *
     * @param o
     *         The observable notifying this observer
     * @param arg
     *         An object argument from the observable o.
     *
     * @author Alexander Komischke
     */
    @Override
    public void update( Observable o, Object arg ) {
        if ( o instanceof Menu ) {
            update( (Menu) o, arg );
        }
        else if ( o instanceof Game ) {
            update( (Game) o, arg );
        }
        else {
            Logger.log( o, arg );
        }
    }

    @Override
    public void update( Menu menu, Object arg ) {
        if ( arg instanceof Button ) {
            Button button = (Button) arg;
            if ( button.getText().equals( "Pacman" ) ) {
                Logger.log( "Pacman-Game selected from Main Menu." );
                this.setGameShown( 0 );
            }
        }
        else {
            Logger.log( "Menu : " + arg.toString() );
        }
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
