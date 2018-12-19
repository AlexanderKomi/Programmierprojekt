package de.hsh.daniel.model;


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

    private void initCards( final int numberOfPairs ) {
        for ( int i = 0 ; i < numberOfPairs ; i++ ) {
            Card c1 = new Card( Resources.cardImages[ i ], i );
            Card c2 = new Card( Resources.cardImages[ i ], i );
            cardList.add( c1 );
            cardList.add( c2 );
        }
        Collections.shuffle( cardList );
    }

    void createGrid() {

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
        int imgCount = iterate( xStart, xStartReset, gridW );
    }

    private int iterate(
            int xStart,
            final int xStartReset,
            final double gridW ) {
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
        return imgCount;
    }

    void onMouseClick( final double x, final double y ) {
        Logger.log( this.getClass() + ": Clicked at : (" + x + ", " + y + ")" );
        Logger.log( this.getClass() + ": TODO : Find the corresponding card, to the given x,y Tuple" );
        for ( Card card : this.cardList ) {
            double[] pos = card.getPos();

        }
    }

    public void draw( Canvas canvas ) {
        for ( Card card : this.cardList ) {
            card.draw( canvas );
        }
    }

    /* -------------------- GETTER & SETTER -------------------- */

}

