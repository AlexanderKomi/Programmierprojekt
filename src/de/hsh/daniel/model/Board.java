package de.hsh.daniel.model;


import common.actor.CollisionCheck;
import common.config.WindowConfig;
import common.util.Logger;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Class represents Gameboard where Cards are laid out
 */
public class Board {

    public static int numberOfPairs = 0; // These are set externally from the menu.


    private Card c1        = null;
    private Card c2        = null;
    private int  cardCount = 0;

    private static final double gridH   = 4;
    private static final int    spacing = 40;
    private static final double imgSize = (double) (WindowConfig.window_height / 4) - (double) spacing / 2;

    private static double          gridW;
    private        ArrayList<Card> cardList = new ArrayList<>();

    Board() {
        gridW = (double) (Board.numberOfPairs / 2);
        initCards( numberOfPairs );
        createGrid();
    }

    /**
     * Initializes cards with number of pairs fetched from menu button
     */
    private void initCards( final int numberOfPairs ) {
        for ( int i = 0 ; i < numberOfPairs ; i++ ) {
            Card c1 = new Card( Resources.cardImages[ i ], i );
            Card c2 = new Card( Resources.cardImages[ i ], i );
            cardList.add( c1 );
            cardList.add( c2 );
        }
        Collections.shuffle( cardList );
    }

    /**
     * Created card grid
     */
    private void createGrid() {

        int xStart;
        int xStartReset;

        if ( numberOfPairs == 6 ) {
            xStart = 300;
            xStartReset = 300;

        }
        else if ( numberOfPairs == 8 ) {
            xStart = 200;
            xStartReset = 200;
        }
        else {
            xStart = 30;
            xStartReset = 30;
        }
        adjustCardPositions( xStart, xStartReset, gridW );
    }

    private void adjustCardPositions( int xStart, final int xStartReset, final double gridW ) {
        int yStart   = 10;
        int imgCount = 0;

        for ( int j = 0 ; j < Board.gridH ; j++ ) {
            for ( int k = 0 ; k < gridW ; k++ ) {

                Card i = this.cardList.get( imgCount );
                i.setPos( xStart, yStart );
                i.setWidth( Board.imgSize );
                i.setHeight( Board.imgSize );

                xStart += (Board.imgSize + Board.spacing);
                imgCount++;
            }
            yStart += Board.imgSize + Board.spacing / 2;
            xStart = xStartReset;
        }
    }

    void onMouseClick( final double mouse_x, final double mouse_y ) {

        //Logger.log(this.getClass() + ": Clicked at : (" + mouse_x + ", " + mouse_y + ")");
        for ( Card card : this.cardList ) {

            boolean isMouseClickOnCard = CollisionCheck.doesCollide( card, mouse_x, mouse_y );

            /*
            Checks if card is clicked and not selected, then turns that card
             */
            if ( isMouseClickOnCard ) {
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
        }

        /*
        If two cards selected then cards are checked if they match
         */
        this.checkMatch();
    }

    /**
     * Checks if two cards match and resets cards after check
     */
    private boolean checkMatch() {
        return checkMatch( this.getC1(), this.getC2() );
    }

    /**
     * Checks if two cards match and resets cards after check
     */
    private boolean checkMatch( Card c1, Card c2 ) {
        if ( c1 == null || c2 == null ) {
            Logger.log( "Cards are empty." );
            return false;
        }

        if ( c1.equals( c2 ) ) {
            Logger.log( "CARDS MATCH" );
            lockCards();
            nullCards();
            return true;

        }
        else {
            Logger.log( "CARDS DON'T MATCH" );
            delay( 2 );
            turnBackCards();
            nullCards();
            return false;
        }

    }

    /**
     * Locks matched cards so that they can not be turned anymore
     */
    private void lockCards() {
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


    public Card getC1() {
        return c1;
    }

    public Card getC2() {
        return c2;
    }
}



