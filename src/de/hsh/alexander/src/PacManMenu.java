package de.hsh.alexander.src;


import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.alexander.src.actor.collectables.DataCoin;
import de.hsh.alexander.src.actor.player.PacMan1;
import de.hsh.alexander.src.actor.player.PacMan2;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public final class PacManMenu extends Observable implements Initializable {

    public static final String   fxml        = "PacManMenu.fxml";
    private             boolean  initialized = false;
    private             DataCoin d;
    private             PacMan1  pacMan1;
    private             PacMan2  pacMan2;

    @FXML
    public VBox   basicPane;
    @FXML
    public Canvas dataCoinCanvas;
    @FXML
    public Canvas player1Canvas;
    @FXML
    public Canvas player2Canvas;
    @FXML
    public Button backButton;
    @FXML
    public Button okButton;


    public void backButtonPressed( ActionEvent actionEvent ) {
        Logger.log( "PacManMenu : Back Button pressed" );
        this.setChanged();
        this.notifyObservers( UpdateCodes.PacMan.mainMenu );
    }

    public void okButtonPressed( ActionEvent actionEvent ) {
        Logger.log( "PacManMenu : Ok Button pressed" );
        this.setChanged();
        this.notifyObservers( UpdateCodes.PacMan.startGame );
    }

    void render() {
        if ( !initialized ) {
            return;
        }
        Platform.runLater( () -> {
            dataCoinCanvas.getGraphicsContext2D().clearRect( 0, 0, dataCoinCanvas.getWidth(), dataCoinCanvas.getHeight() );
            player1Canvas.getGraphicsContext2D().clearRect( 0, 0, player1Canvas.getWidth(), player1Canvas.getHeight() );
            player2Canvas.getGraphicsContext2D().clearRect( 0, 0, player2Canvas.getWidth(), player2Canvas.getHeight() );

            d.draw( dataCoinCanvas );
            pacMan1.draw( player1Canvas );
            pacMan2.draw( player2Canvas );

        } );

    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

        d = new DataCoin( dataCoinCanvas.getWidth() / 2 - 25, dataCoinCanvas.getHeight() / 2 - 25 );
        pacMan1 = new PacMan1( player1Canvas.getWidth() / 2, player1Canvas.getHeight() / 2 );
        pacMan2 = new PacMan2( player2Canvas.getWidth() / 2, player2Canvas.getHeight() / 2 );

        initialized = true;
    }
}
