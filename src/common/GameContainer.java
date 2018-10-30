package common;

import common.config.WindowConfig;
import de.hsh.Julian.Leertastenklatsche;
import de.hsh.alexander.engine.FXGameContainer;
import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.GameMenu;
import de.hsh.alexander.engine.game.Menu;
import de.hsh.alexander.game.PacManCoop;
import de.hsh.alexander.util.Logger;
import de.hsh.amir.AmirsGame;
import de.hsh.daniel.RAM;
import de.hsh.dennis.DennisGame;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class GameContainer extends FXGameContainer {

    @Override
    protected Stage configWindow( Stage primaryStage ) {
        primaryStage.setX( WindowConfig.window_width );
        primaryStage.setY( WindowConfig.window_height );
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
            if ( arg instanceof ActionEvent ) {
                ActionEvent event = (ActionEvent) arg;
                if ( event.getSource() instanceof Button ) {
                    Button button = (Button) event.getSource();
                    if ( button.getText().equals( "zurück" ) ) {
                        Logger.log( arg );
                        this.showMainMenu();
                    }
                }
            }
        }
        else if ( game instanceof AmirsGame ) {
            Logger.log( game, arg );
        }
        else if ( game instanceof RAM ) {
            Logger.log( game, arg );
        }
        else if ( game instanceof DennisGame ) {
            Logger.log( game, arg );
        }
        else if ( game instanceof Leertastenklatsche ) {
            Logger.log( game, arg );
        }
        else {
            Logger.log( "Arg : ", arg );
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
        else if ( o instanceof GameMenu ) {
            update( (GameMenu) o, arg );
        }
        else {
            System.out.println( "Observable" );
            Logger.log( o, arg );
        }
    }

    @Override
    public void update( Menu menu, Object arg ) {
        if ( arg instanceof Button ) {
            Button button = (Button) arg;
            if ( button.getText().equals( "Pacman" ) ) {
                Logger.log( "Pacman-Game selected from Main Menu." );
                this.getStage().setTitle( "PacMan Coop" );
                this.setGameShown( 0 );
            }
        }
        else {
            Logger.log( "Menu : " + arg.toString() );
        }
    }

    @Override
    public void update( GameMenu gameMenu, Object arg ) {
        if ( arg instanceof ActionEvent ) {
            ActionEvent event = (ActionEvent) arg;
            if ( event.getSource() instanceof Button ) {
                Button button = (Button) event.getSource();
                if ( button.getText().equals( "zurück" ) ) {
                    Logger.log( arg );
                    this.showMainMenu();
                }
            }
        }
        else {
            Logger.log( "GameMenu : " + arg.toString() );
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
