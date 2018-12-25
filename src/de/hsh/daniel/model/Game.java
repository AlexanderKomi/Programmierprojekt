package de.hsh.daniel.model;

import common.util.PlaySound;
import javafx.scene.canvas.Canvas;


/**
 * THis is the game started
 */
public class Game {

    private Board board;
    private Player p1;
    private Player p2;

    //TODO: Tried to draw second card if selected but does not work
    public void render(Canvas gameCanvas, final int fps) {
        board.draw(gameCanvas);
        do{
            Card c = board.getC2();
            board.draw(gameCanvas);
        } while((board.getC2() != null));
    }

    /*
    Sets up Board on canvas and creates two Player objects
     */
    public void initialize(Canvas gameCanvas) {
        board = new Board();
        p1 = new Player();
        p2 = new Player();

        gameCanvas.setOnMouseClicked(e -> {

            board.onMouseClick(e.getX(), e.getY());

            /*
            If two cards selected then cards are checked if they match
             */
            if (!board.cardsEmpty()) {
                board.checkMatch(board.getC1(), board.getC2());
            }
        });


    }
}




