package de.hsh.daniel.model;


import common.actor.CollisionCheck;
import common.config.WindowConfig;
import common.util.Logger;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;


/**
 * Class represents Gameboard where Cards are laid out
 */
public class Board {

    public static byte numberOfPairs = 0; // These are set externally from the menu.

    private Card    c1               = null;
    private Card    c2               = null;
    private boolean firstCardClicked = false;

    static final byte   gridH   = 4;
    static final int    spacing = 40;
    static final double imgSize = (double) (WindowConfig.window_height / 4) - (double) spacing / 2;

    static        int             gridW;
    private final ArrayList<Card> cardList;

    Board() {
        gridW = (Board.numberOfPairs / 2);
        cardList = BoardUtilities.initCards( numberOfPairs );
    }

    /**
     * A mouse click happened to the board.
     */
    void onMouseClick( final double mouse_x, final double mouse_y ) {
        for ( Card card : this.cardList ) {
            if ( CollisionCheck.doesCollide( card, mouse_x, mouse_y ) ) {
                onClickedCard( card );
                this.checkMatch();
            }
        }
    }

    /**
     * Checks if card is clicked and not selected, then turns that card
     */
    private void onClickedCard( Card card ) {
        if ( !card.isCardMatched() ) {
            if ( !firstCardClicked ) {
                c1 = card;
                firstCardClicked = true;
            }
            else {
                c2 = card;
            }
        }

        if ( !card.isCardMatched() ) {
            if ( c1 != null && c2 == null ) {
                c1.turn();
            }
            else if ( c1 != null ) {
                c2.turn();
            }
        }
    }

    /**
     * Checks if two cards match and resets cards after check
     * If two cards selected then cards are checked if they match
     */
    private void checkMatch() {
        if ( c1 == null || c2 == null ) {
            Logger.log( "Cards are empty." );
            return;
        }

        if ( c1.equals( c2 ) ) {
            Logger.log( "CARDS MATCH" );
            setCardsMatched();
        }
        else {
            Logger.log( "CARDS DON'T MATCH" );
            delay( 2 );
            turnBackCards();
        }
        nullCards();

    }

    /**
     * Locks matched cards so that they can not be turned anymore
     */
    private void setCardsMatched() {
        c1.setCardMatched( true );
        c2.setCardMatched( true );
    }

    /**
     * Turns both selected cards facedown again
     */
    private void turnBackCards() {
        c1.turn();
        c2.turn();
    }

    /**
     * Sets cards to null
     */
    private void nullCards() {
        c1 = null;
        c2 = null;
        firstCardClicked = false;
    }

    /**
     * Delays time for given amount
     *
     * @param time
     *         is converted from sec. to ms.
     */
    private void delay( int time ) {
        time = (time * 1000);
        try {
            Thread.sleep( time );
        }
        catch ( InterruptedException e1 ) {
            e1.printStackTrace();
        }
    }


    public void draw( Canvas canvas ) {
        for ( Card card : this.cardList ) {
            card.draw( canvas );
        }
    }

    /* -------------------- GETTER & SETTER -------------------- */
}



