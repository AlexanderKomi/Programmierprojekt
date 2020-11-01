package de.hsh.dennis.controller;

import common.config.WindowConfig;
import common.updates.UpdateCodes;
import common.logger.Logger;
import de.hsh.dennis.model.GameModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class Level_controller extends Observable implements Initializable {

    private static boolean initialized = false;
    private static double canvasX = WindowConfig.window_width;
    private static double canvasY = WindowConfig.window_height - 100;

    @FXML
    private HBox hbox_1;

    @FXML
    private VBox vbox_1;

    @FXML
    private Canvas canvas;

    @FXML
    private TextField tf_score;

    @FXML
    private TextField tf_health;

    @FXML
    void key_pressed(KeyEvent event) {
        setChanged();
        notifyObservers(event.getCode());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!initialized) {
            Logger.INSTANCE.log("initializing ...");
            canvas.setWidth(canvasX);
            canvas.setHeight(canvasY);
            tf_health.setText("100");
            tf_score.setText("0");
            tf_health.setOnKeyPressed(this::key_pressed);
            tf_score.setOnKeyPressed(this::key_pressed);

            tf_score.textProperty().bind(GameModel.score_string);
            tf_health.textProperty().bind(GameModel.health_string);

            this.canvas.setFocusTraversable(true);  //!!!must have!!! for a working canvas
            Logger.INSTANCE.log("initializing DONE");
        }
    }

    public void passCanvas() {
        setChanged();
        notifyObservers(canvas);
        setChanged();
        notifyObservers(UpdateCodes.Dennis.gameReady);
    }
}
