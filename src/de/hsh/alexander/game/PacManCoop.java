package de.hsh.alexander.game;

import de.hsh.alexander.engine.game.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Observable;
import java.util.Observer;

public class PacManCoop extends Game implements Observer {

    private PacManMenu gameMenu;
    private BorderPane gamePane;

    public PacManCoop( Observer o ) {
        super( o, "Pacman Coop" );
    }

    @Override
    public Pane initGameContentWindow( Observer observer ) {
        initRessources();
        gameMenu = new PacManMenu( this );
        return gameMenu.getMenuPane();
    }

    private void initRessources() {
        BorderPane bp = new BorderPane();
        bp.setCenter( new Text( "Game lauched" ) );
        this.gamePane = bp;
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
}
