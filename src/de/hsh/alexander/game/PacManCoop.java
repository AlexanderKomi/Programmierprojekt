package de.hsh.alexander.game;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
        super( o, "Pacman Coop" );
        if ( !loadMenuFXML() ) { // In case FXML could not be loaded, a default pane is set
            this.setGameContentPane( new Pane() );
        }
    }

    private boolean loadMenuFXML() {
        try {
            VBox node = FXMLLoader.load( getClass().getResource( "PacManMenu.fxml" ) );
            this.gameMenu = new PacManMenu();
            this.gameMenu.setMenuPane( node );
            this.gameMenu.addObserver( this );
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
        if ( arg instanceof ActionEvent ) {
            ActionEvent event = (ActionEvent) arg;
            if ( event.getSource() instanceof Button ) {
                Button button = (Button) event.getSource();
                if ( button.getText().equals( "zurück" ) ) {
                    this.notifyObservers( "Mainmenu" );
                }
                else if ( button.getText().equals( "OK" ) ) {
                    this.setGameContentPane( gamePane );
                    this.notifyObservers( "Start Game" );
                }
            }
        }
        Logger.log( "In PacMan Coop update : from observable : " + o + " Argument could not be parsed : " + arg );
    }

    public Node getStartingScreen() {
        return this.gameMenu.getMenuPane();
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

    }
}
