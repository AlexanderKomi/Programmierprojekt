package de.hsh.amir.controller;

import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.amir.logic.Gegner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class AmirsMainMenuController extends Observable {

    public static final String fxml = "view/AmirsMenu.fxml";
    public static final String fxmlGameOver = "view/AmirsMenuGameOver.fxml";
    public static final int GEGNER_SPEED = 3;
    public static int LEVEL_NUMBER = 1;
    private boolean initialized = false;
    public static boolean gameStarted = false;

    @FXML
    private VBox vbox_1;

    @FXML
    private Button startButton;

    @FXML
    private Button level1Button;

    @FXML
    private Button level2Button;

    @FXML
    private Button level3Button;

    @FXML
    private Button exitButton;

    @FXML
    private Label titleLabel;


    @FXML
    void button_clicked(ActionEvent event) {
        String id = getId(event);
        this.setChanged();
        if (id.equals("startGameButton")) {
            this.notifyObservers(UpdateCodes.Amir.startGame);
        } else if (id.equals("exitButton")) {
            this.notifyObservers(UpdateCodes.DefaultCodes.exitToMainGUI);
        } else if (id.equals("level1Button")) {
            setGegnerSpeed(GEGNER_SPEED);
            level1Button.setDisable(true);
            level2Button.setDisable(false);
            level3Button.setDisable(false);
            LEVEL_NUMBER = 1;
            Logger.log(id);
        } else if (id.equals("level2Button")) {
            setGegnerSpeed(2 * GEGNER_SPEED);
            level1Button.setDisable(false);
            level2Button.setDisable(true);
            level3Button.setDisable(false);
            LEVEL_NUMBER = 2;
            Logger.log(id);
        } else if (id.equals("level3Button")) {
            setGegnerSpeed(2 * GEGNER_SPEED);
            level1Button.setDisable(false);
            level2Button.setDisable(false);
            level3Button.setDisable(true);
            LEVEL_NUMBER = 3;
            Logger.log(id);
        } else {
            this.notifyObservers(id);
        }
    }

//    private void changeLabel(String newLabel) {
//        if (!initialized) {
//            return;
//        }
//        Platform.runLater(() -> titleLabel.setText(newLabel));
//    }
//
//    /**
//     * Wann muss gameStarted auf true gesetzt werden?
//     */
//    public void switchLabels() {
//        if (AmirsMainMenuController.gameStarted) {
//            changeLabel("Virus Collector");
//        } else {
//            changeLabel("Gewonnen!");
//        }
//    }
//
//    public void render(final int fps) {
//        if (!initialized) {
//            return;
//        }
//        switchLabels();
//    }

    /**
     * Setzt Gegnergeschwindigkeit global auf den übergebenen Wert.
     *
     * @param speed
     */
    private void setGegnerSpeed(double speed) {
        Gegner.setGegnerSpeed(speed);
    }

    private String getId(ActionEvent event) {
        return ((Node) event.getSource()).getId();
    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        initialized = true;
//        gameStarted = true;
//    }
}
