package de.hsh.daniel.model;

import common.actor.CollisionCheck;
import common.config.WindowConfig;
import javafx.scene.canvas.Canvas;

final class GUIBoard extends Board {


    private static final byte   gridH   = 4;
    private static final int    spacing = 40;
    private static final double imgSize = (double) (WindowConfig.window_height / 4) - (double) spacing / 2;
    private static       int    gridW;

    GUIBoard() {
        super();
        gridW = (Board.numberOfPairs / 2);
        this.cardList = BoardUtilities.createGrid(
                cardList,
                numberOfPairs,
                gridW,
                gridH,
                imgSize,
                spacing );
    }

    /**
     * A mouse click happened to the board.
     */
    void onMouseClick( final double mouse_x, final double mouse_y ) {
        for ( Card card : this.cardList ) {
            if ( CollisionCheck.doesCollide( card, mouse_x, mouse_y ) ) {
                onClickedCard( card );
            }
        }
    }

    /**
     * Checks if card is clicked and not selected, then turns that card
     */
    private void onClickedCard( Card card ) {
        matchCards( card );
    }

    public void draw( Canvas canvas ) {
        for ( Card card : this.cardList ) {
            card.draw( canvas );
        }
    }
}
