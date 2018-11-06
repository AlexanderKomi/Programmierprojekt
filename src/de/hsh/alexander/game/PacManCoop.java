package de.hsh.alexander.game;

import common.updates.UpdateCodes;
import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.util.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class PacManCoop extends Game implements Initializable {

    private PacManMenu gameMenu;
    private BorderPane gamePane;
    public PacManCoop( Observer o ) {
        super( o, UpdateCodes.PacMan.gameName );
        if ( !loadMenuFXML() ) { // In case FXML could not be loaded, a default pane is set
            this.setGameContentPane( new Pane() );
        }
    }

    private boolean loadMenuFXML() {
        try {
            this.gameMenu = new PacManMenu( this );
            VBox node = FXMLLoader.load( PacManMenu.class.getResource( PacManMenu.fxml ) );

            //this.gameMenu.addObserver( this );
            this.gameMenu.setMenuPane( node );
            this.setGameContentPane( this.gameMenu.getMenuPane() );
            return true;
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        return false;
    }

    /* Currently not used... */
    @Override
    public void update( Observable o, Object arg ) {
        if ( o instanceof PacManMenu ) {
            if ( arg instanceof String ) {
                String message = (String) arg;
                switch ( message ) {
                    case UpdateCodes.PacMan.startGame:
                        this.notifyObservers( message );
                        break;
                    case UpdateCodes.PacMan.mainMenu:
                        this.notifyObservers( message );
                        break;
                    default:
                        logParsingError( o, arg );
                        break;
                }
            }
            else {
                logParsingError( o, arg );
            }
        }
        else {
            logParsingError( o, arg );
        }
    }

    private void logParsingError( Observable o, Object arg ) {
        Logger.log( "In PacMan Coop update : from observable : " + o + " Argument could not be parsed : " + arg );
    }

    public Node getStartingScreen() {
        return this.gameMenu.getMenuPane();
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

    }
}
