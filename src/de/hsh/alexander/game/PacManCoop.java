package de.hsh.alexander.game;

import de.hsh.alexander.engine.game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

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
        loadMenuFXML();
        this.setGameContentPane( new Pane() );
        //gameMenu.addObserver( o );
    }

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
    }

    private void loadMenuFXML() {
        try {
            Object node = FXMLLoader.load( getClass().getResource( "PacManMenu.fxml" ) );
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

    }
}
