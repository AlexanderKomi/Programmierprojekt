package de.hsh.alexander.game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class PacManGame extends Observable implements Initializable {

    public static  String     fxml = "PacManGame.fxml";
    private static Observer   temp;
    @FXML
    public         Canvas     gameCanvas;
    @FXML
    public         AnchorPane gamePane;

    public PacManGame() {}

    public PacManGame( Observer o ) {
        temp = o;
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        this.addObserver( temp );
        GraphicsContext c = this.gameCanvas.getGraphicsContext2D();
        c.fillText( "Test text", 100.0, 100.0 );
    }
}
