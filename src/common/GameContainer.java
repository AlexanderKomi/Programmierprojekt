package common;

import de.hsh.Julian.Leertastenklatsche;
import de.hsh.alexander.engine.FXGameContainer;
import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.GameMenu;
import de.hsh.alexander.engine.game.Games;
import de.hsh.alexander.engine.game.Menu;
import de.hsh.alexander.game.PacManCoop;
import de.hsh.alexander.util.Logger;
import de.hsh.amir.AmirsGame;
import de.hsh.daniel.RAM;
import de.hsh.dennis.DennisGame;
import de.hsh.kevin.KevinGame;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class GameContainer extends FXGameContainer {

    @Override
    protected Stage configWindow( Stage primaryStage ) {
        primaryStage.setResizable( false );
        return primaryStage;
    }

    @Override
    public Games createGames( Observer container ) {
        return new Games(
                new PacManCoop( container ),
                new AmirsGame( container ),
                new RAM( container ),
                new KevinGame( container ),
                new Leertastenklatsche( container ),
                new DennisGame( container )
        );
    }

    @Override
    protected Menu configMainMenu( Observer container, Games games ) {
        return new common.MainMenu( container, games );
    }

    @Override
    public void update( Game game, Object arg ) {
        if ( game instanceof PacManCoop ) {
            update( (PacManCoop) game, arg );
        }
        else if ( game instanceof AmirsGame ) {
            update( (AmirsGame) game, arg );
        }
        else if ( game instanceof RAM ) {
            update( (RAM) game, arg );
        }
        else if ( game instanceof DennisGame ) {
            update( (DennisGame) game, arg );
        }
        else if ( game instanceof Leertastenklatsche ) {
            update( (Leertastenklatsche) game, arg );
        }
        else if ( game instanceof KevinGame ) {
            update( (KevinGame) game, arg );
        }
        else {
            Logger.log( game, arg );
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
        if ( menu instanceof MainMenu ) {
            update( (MainMenu) menu, arg );
        }
        else {
            Logger.log( "Not a known menu : " + menu, arg );
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
            Logger.log( gameMenu, arg );
        }
    }

    public void update( MainMenu menu, Object arg ) {

        if ( arg instanceof Button ) {

            Button button   = (Button) arg;
            String gameName = button.getText();
            Games  games    = this.getGames();
            Game   g        = games.get( gameName );
            this.setGameShown( g );
            this.getStage().setTitle( gameName );
            Logger.log( "Game set : " + gameName );
        }
        else {
            Logger.log( "Can not parse : update(MainMenu, Object)" );
        }
        Logger.log( menu, arg );
    }

    public void update( PacManCoop pacManCoop, Object arg ) {
        if ( arg instanceof ActionEvent ) {
            ActionEvent event = (ActionEvent) arg;
            if ( event.getSource() instanceof Button ) {
                Button button     = (Button) event.getSource();
                String buttonText = button.getText();
                if ( buttonText.equals( "zurück" ) ) {
                    this.showMainMenu();
                }
                else if ( buttonText.equals( "OK" ) ) {
                    // TODO : Implement change to gameplay screen
                }
            }
        }
        Logger.log( pacManCoop, arg );
    }

    public void update( AmirsGame game, Object arg ) {
        Logger.log( game, arg );
    }

    public void update( RAM game, Object arg ) {
        Logger.log( game, arg );
    }

    public void update( DennisGame game, Object arg ) {
        Logger.log( game, arg );
    }

    public void update( Leertastenklatsche game, Object arg ) {
        Logger.log( game, arg );
    }

    public void update( KevinGame game, Object arg ) {
        Logger.log( game, arg );
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

    @Override
    public String toString() {
        return "GameContainer(" +
               "superclass:" + super.toString() +
               ")";
    }
}
