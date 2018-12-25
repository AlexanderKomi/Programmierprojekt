package de.hsh.daniel.model;

import java.util.ArrayList;
import java.util.Collections;

import static de.hsh.daniel.model.Board.gridW;
import static de.hsh.daniel.model.Board.numberOfPairs;

final class BoardUtilities {

    /**
     * Initializes cards with number of pairs fetched from menu button
     */
    static void initCards( final int numberOfPairs, ArrayList<Card> cardList ) {
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
    static void createGrid( ArrayList<Card> cardList ) {

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
        adjustCardPositions( cardList, xStart, xStartReset, gridW );
    }

    private static void adjustCardPositions( ArrayList<Card> cardList, int xStart, final int xStartReset, final double gridW ) {
        int yStart   = 10;
        int imgCount = 0;

        for ( int j = 0 ; j < Board.gridH ; j++ ) {
            for ( int k = 0 ; k < gridW ; k++ ) {

                Card i = cardList.get( imgCount );
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

}
