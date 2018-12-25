package de.hsh.daniel.model;

import javafx.scene.canvas.Canvas;


/**
 * THis is the game started
 */
public class Game {

    private Board board;
    private Player p1;
    private Player p2;

    public void render(Canvas gameCanvas, final int fps) {
        board.draw(gameCanvas);
        if(board.getC2() != null){
            Card c = board.getC2();
            double[] pos = c.getPos();
            c.draw(gameCanvas, pos);
        }
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

            if (!board.cardsEmpty()) {
                boolean match = board.checkMatch(board.getC1(), board.getC2());
                if (!match) {
                    board.delay(2);
                    board.nullCards();
                }
            }
        });

    }
}




