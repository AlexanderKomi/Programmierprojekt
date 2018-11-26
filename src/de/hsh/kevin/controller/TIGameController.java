package de.hsh.kevin.controller;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Observable;
import java.util.Observer;

public class TIGameController extends GameEntryPoint {

    public static final String fxml = "../res/TIGame.fxml";
    
    public static VBox gameBox;
    
    @FXML
    public Button btn_score;
    
    @FXML
    public Label lbl_leben;
    
    @FXML
    public Label lbl_score;
    
    public TIGameController( Observer o ) {
        super( o, WindowConfig.kevin_title );

    }

    @Override
    public void update( Observable o, Object arg ) {

    }

    @Override
    public void render() {

    }

    @FXML
    void initialize() {

    }
    
    // Vorläufiger Button zum Testen der Funktionalität
    @FXML
    void scorePressed(ActionEvent event) {

    }
}
