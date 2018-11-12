package de.hsh.kevin;

import de.hsh.alexander.engine.game.GameMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;

public class KevinMenu extends GameMenu {
    
    public static final String fxml = "res/TIMenu.fxml";
    
    @FXML
    public Button btn_start;
    
    @FXML
    public Button btn_sound;
    
    @FXML
    public Button btn_schwierigkeit;
    
    @FXML
    public Button btn_sammlung;
    
    @FXML
    public Button btn_exit;
    
    
    
    public KevinMenu( Observer observer ) {
        super( observer );
        
    }

    @Override
    protected Pane initMenuPane() {
        return new Pane();
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

    }

    @FXML
    void difficultyPressed(ActionEvent event) {

    }

    @FXML
    void exitPressed(ActionEvent event) {

    }

    @FXML
    void sammlungPressed(ActionEvent event) {

    }

    @FXML
    void soundPressed(ActionEvent event) {

    }

    @FXML
    void startGamePressed(ActionEvent event) {

    }
}
