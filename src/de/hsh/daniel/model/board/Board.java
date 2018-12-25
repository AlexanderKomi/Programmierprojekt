package de.hsh.daniel.model.board;


import common.util.Logger;
import de.hsh.daniel.model.Card;

import java.util.ArrayList;


/**
 * Class represents Gameboard where Cards are laid out
 */
public class Board {

    public static byte numberOfPairs = 0; // These are set externally from the menu.

    private static Card            c1               = null;
    private static Card            c2               = null;
    private static boolean         firstCardClicked = false;
    static         ArrayList<Card> cardList;


    Board() {
        cardList = BoardUtilities.initCards( numberOfPairs );
    }

    void matchCards( Card card ) {
        if ( !card.isCardMatched() ) {
            if ( !firstCardClicked ) {
                c1 = card;
                firstCardClicked = true;
            }
            else {
                c2 = card;
            }

            if ( c1 != null && c2 == null ) {
                c1.turn();
            }
            else if ( c1 != null ) {
                c2.turn();
            }
        }
        this.checkMatch();
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
            BoardUtilities.delay( 2 );
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



}



