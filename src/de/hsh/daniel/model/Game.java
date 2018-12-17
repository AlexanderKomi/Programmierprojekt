package de.hsh.daniel.model;

import common.config.WindowConfig;
import common.util.Logger;
import de.hsh.daniel.controller.RamGame_controller;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;


/**
 * THis is the game started
 */
public class Game {

    private GraphicsContext gc;
    @FXML
    private Canvas gameCanvas;


    public void render(Canvas gameCanvas, int fps) {
    }

    //TODO: GET NUMBER OF PAIRS FROM BUTTON AND PARSE TO METHOD

}

