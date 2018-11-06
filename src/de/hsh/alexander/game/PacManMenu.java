package de.hsh.alexander.game;

import common.updates.UpdateCodes;
import de.hsh.alexander.engine.game.GameMenu;
import de.hsh.alexander.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;

public class PacManMenu extends GameMenu {
    @FXML
    public        VBox     basicPane;
    public static Observer observer;
    @FXML
    public        Button   backButton;
    @FXML
    public        Button   okButton;

    public PacManMenu() {}

    public PacManMenu( Observer o ) {
        observer = o;
    }


    @Override
    protected Pane initMenuPane() {
        return new Pane();
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        this.addObserver( observer );
        okButton.setOnAction( e -> {
            Logger.log( "PacManMenu : Ok Button pressed" );
            if ( this.countObservers() < 1 ) {
                try {
                    throw new Exception( "Less than one observer are registered." );
                }
                catch ( Exception e1 ) {
                    e1.printStackTrace();
                }
            }

            this.setChanged();
            this.notifyObservers( UpdateCodes.PacMan.startGame );
        } );
        backButton.setOnAction( e -> {
            Logger.log( "PacManMenu : Back Button pressed" );
            if ( this.countObservers() < 1 ) {
                try {
                    throw new Exception( "Less than one observer are registered." );
                }
                catch ( Exception e1 ) {
                    e1.printStackTrace();
                }
            }
            this.setChanged();
            this.notifyObservers( UpdateCodes.PacMan.mainMenu );
        } );

    }

    public void backButtonPressed( ActionEvent actionEvent ) {
        Logger.log( "PacManMenu : Back Button pressed" );
        Logger.log( "PacManMenu : count observers : " + this.countObservers() );
        this.setChanged();
        this.notifyObservers( "Back" );
    }

    public void okButtonPressed( ActionEvent actionEvent ) {
        Logger.log( "PacManMenu : Ok Button pressed" );
        Logger.log( "PacManMenu : count observers : " + this.countObservers() );
        this.setChanged();
        this.notifyObservers( "Ok" );
    }
}
