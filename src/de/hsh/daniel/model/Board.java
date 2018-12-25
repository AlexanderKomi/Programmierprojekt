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

    private Card c1        = null;
    private Card c2        = null;
    private byte cardCount = 0;

    static final byte   gridH   = 4;
    static final int    spacing = 40;
    static final double imgSize = (double) (WindowConfig.window_height / 4) - (double) spacing / 2;

    static        int             gridW;
    private final ArrayList<Card> cardList;

    Board() {
        gridW = (Board.numberOfPairs / 2);
        cardList = BoardUtilities.initCards( numberOfPairs );
    }

    void onMouseClick( final double mouse_x, final double mouse_y ) {

        for ( Card card : this.cardList ) {
            boolean isMouseClickOnCard = CollisionCheck.doesCollide( card, mouse_x, mouse_y );
            if ( isMouseClickOnCard ) {
                onClickedCard( card );
            }
        }

        this.checkMatch();
    }

    /**
     * Checks if card is clicked and not selected, then turns that card
     */
    private void onClickedCard( Card card ) {
        if ( cardCount == 0 && !card.isCardMatched() ) {
            c1 = card;
            ++cardCount;
        }
        else if ( cardCount == 1 ) {
            c2 = card;
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
    private boolean checkMatch() {
        if ( c1 == null || c2 == null ) {
            Logger.log( "Cards are empty." );
            return false;
        }

        if ( c1.equals( c2 ) ) {
            Logger.log( "CARDS MATCH" );
            setCardsMatched();
            nullCards();
            return true;

        }
        else {
            Logger.log( "CARDS DON'T MATCH" );
            delay( 1 );
            turnBackCards();
            nullCards();
            return false;
        }

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
        cardCount = 0;
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



