package de.hsh.daniel.model.board;

import common.actor.CollisionCheck;
import common.config.WindowConfig;
import de.hsh.daniel.model.Card;
import javafx.scene.canvas.Canvas;

public final class GUIBoard extends Board {


    private static final byte   gridH   = 4;
    private static final int    spacing = 40;
    private static final double imgSize = (double) (WindowConfig.window_height / 4) - (double) spacing / 2;
    private static       int    gridW;

    public GUIBoard() {
        super();
        gridW = (Board.numberOfPairs / 2);
        cardList = BoardUtilities.createGrid(
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
    public void onMouseClick( final double mouse_x, final double mouse_y ) {
        for ( Card card : cardList ) {
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
        for ( Card card : cardList ) {
            card.draw( canvas );
        }
    }
}
