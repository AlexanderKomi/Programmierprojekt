package de.hsh.alexander.game;

import de.hsh.alexander.engine.game.GameMenu;
import de.hsh.alexander.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PacManMenu extends GameMenu {
    @FXML
    public VBox basicPane;

    public PacManMenu() {}

    @Override
    protected Pane initMenuPane() {
        return new Pane();
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

    }

    public void backButtonPressed( ActionEvent actionEvent ) {
        Logger.log( "PacManMenu : Back Button pressed" );
        this.setChanged();
        this.notifyObservers( "Back" );
    }

    public void okButtonPressed( ActionEvent actionEvent ) {
        Logger.log( "PacManMenu : Ok Button pressed" );
        this.setChanged();
        this.notifyObservers( "Ok" );
    }
}
