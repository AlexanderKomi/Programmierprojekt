package de.hsh.alexander;

import common.config.WindowConfig;
import common.engine.game.Game;
import common.events.KeyEventManager;
import common.events.MouseEventManager;
import common.updates.UpdateCodes;
import common.util.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class PacManController extends Game implements Initializable {

    private PacManMenu gameMenu;
    private PacManGame game;

    public PacManController( Observer o ) {
        super( o, WindowConfig.alexander_title );
        if ( !loadMenuFXML() ) { // In case FXML could not be loaded, a default pane is set
            this.setGameContentPane( new Pane() );
        }
        loadGameFXML();
    }

    private boolean loadGameFXML() {
        try {
            this.game = new PacManGame( this );
            PacManGame.gamePane = FXMLLoader.load( PacManGame.class.getResource( PacManGame.fxml ) );
            return true;
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        return false;
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
        this.game.init();
        if ( o instanceof PacManMenu ) {
            update( (PacManMenu) o, arg );
        }
        else if ( o instanceof KeyEventManager ) {
            update( (KeyEventManager) o, arg );
        }
        else if ( o instanceof MouseEventManager ) {
            update( (MouseEventManager) o, arg );
        }
        else {
            logParsingError( o, arg );
        }
    }

    private void update( PacManMenu o, Object arg ) {
        if ( arg instanceof String ) {
            String message = (String) arg;
            switch ( message ) {
                case UpdateCodes.PacMan.startGame:
                    this.setGameContentPane( PacManGame.gamePane );
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

    public void update( KeyEventManager o, Object arg ) {
        if ( arg instanceof KeyEvent ) {
            KeyEvent keyEvent = (KeyEvent) arg;
            this.game.movePacMan1( keyEvent );
        }
    }

    public void update( MouseEventManager o, Object arg ) {}

    private void logParsingError( Observable o, Object arg ) {
        Logger.log( "In PacMan Coop update : from observable : " + o + " Argument could not be parsed : " + arg );
    }

    public void render() {
        this.game.render();
    }

    public Node getStartingScreen() {
        return this.gameMenu.getMenuPane();
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

    }
}
