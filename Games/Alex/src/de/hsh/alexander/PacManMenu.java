package de.hsh.alexander;


import common.actor.Drawable;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.alexander.actor.collectables.DataCoin;
import de.hsh.alexander.actor.player.PacMan1;
import de.hsh.alexander.actor.player.PacMan2;
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
    private static      DataCoin d;
    private static      PacMan1  pacMan1;
    private static      PacMan2  pacMan2;

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
        Logger.INSTANCE.log( "PacManMenu : Back Button pressed" );
        this.setChanged();
        this.notifyObservers( UpdateCodes.PacMan.mainMenu );
    }

    public void okButtonPressed( ActionEvent actionEvent ) {
        Logger.INSTANCE.log( "PacManMenu : Ok Button pressed" );
        this.setChanged();
        this.notifyObservers( UpdateCodes.PacMan.startGame );
    }

    void render() {
        if ( !initialized ) {
            return;
        }
        clearAndDraw( pacMan1, player1Canvas );
        clearAndDraw( pacMan2, player2Canvas );
        clearAndDraw( d, dataCoinCanvas );
    }

    static void clearAndDraw( Drawable p, Canvas canvas ) {
        clearCanvas( canvas );
        p.draw( canvas );
    }

    private static void clearCanvas( Canvas canvas ) {
        canvas.getGraphicsContext2D().clearRect( 0, 0, canvas.getWidth(), canvas.getHeight() );
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

        d = new DataCoin( 0, 0, 2 );
        pacMan1 = new PacMan1( 0, 0 );
        pacMan2 = new PacMan2( 0, 0 );

        initialized = true;
    }
}
