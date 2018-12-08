package de.hsh.daniel.controller;

import common.util.Logger;
import de.hsh.daniel.model.Board;
import de.hsh.daniel.model.Card;
import de.hsh.daniel.model.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

/** This is the game fxml-controller started */
public class RamGame_controller extends Observable implements Initializable {

    public static final String          fxml        = "view/RAMGame.fxml";
    private             Game            game        = new Game();
    @FXML
    private             Canvas          gameCanvas  = new Canvas();
    //GraphicsContext gc = gameCanvas.getGraphicsContext2D();




    public void render( int fps ) {
        //Logger.log( "render" );
        game.render( gameCanvas, fps );
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
        Board board = new Board();
        board.initCards(4);

        Image img = new Image("de/hsh/daniel/images/1.png");
        gc.drawImage(img, 10,10,20,20);


    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        Logger.log( this.getClass() + ": initialized" );
        gameCanvas.setFocusTraversable( true );



    }
}

