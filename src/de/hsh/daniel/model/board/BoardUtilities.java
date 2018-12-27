package de.hsh.daniel.model.board;

import de.hsh.daniel.model.Card;
import de.hsh.daniel.model.Resources;

import java.util.ArrayList;
import java.util.Collections;

public final class BoardUtilities {

    private BoardUtilities() {}

    /**
     * Initializes cards with number of pairs fetched from menu button
     */
    static ArrayList<Card> initCards( final int numberOfPairs ) {
        ArrayList<Card> cardList = new ArrayList<>();
        for ( int i = 0 ; i < numberOfPairs ; i++ ) {
            Card c1 = new Card( Resources.cardImages[ i ], i );
            Card c2 = new Card( Resources.cardImages[ i ], i );
            cardList.add( c1 );
            cardList.add( c2 );
        }
        Collections.shuffle( cardList );
        return cardList;
    }

    /**
     * Created card grid
     */
    static ArrayList<Card> createGrid( final ArrayList<Card> cardList,
                                       final byte numberOfPairs,
                                       final double gridW,
                                       final double gridH,
                                       final double imgSize,
                                       final double spacing ) {

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
        return adjustCardPositions( cardList, xStart, xStartReset, gridW,
                                    gridH,
                                    imgSize,
                                    spacing );
    }

    private static ArrayList<Card> adjustCardPositions(
            final ArrayList<Card> cardList,
            int xStart,
            final int xStartReset,
            final double gridW,
            final double gridH,
            final double imgSize,
            final double spacing
                                                      ) {
        int yStart   = 10;
        int imgCount = 0;

        for ( int j = 0 ; j < gridH ; j++ ) {
            for ( int k = 0 ; k < gridW ; k++ ) {

                Card i = cardList.get( imgCount );
                i.setPos( xStart, yStart );
                i.setWidth( imgSize );
                i.setHeight( imgSize );

                xStart += (imgSize + spacing);
                imgCount++;
            }
            yStart += imgSize + spacing / 2;
            xStart = xStartReset;
        }
        return cardList;
    }

    /**
     * Delays time for given amount
     *
     * @param time
     *         is converted from sec. to ms.
     */
    public static void delay( final int time ) {
        try {
            Thread.sleep( time * 1000 );
        }
        catch ( InterruptedException e1 ) {
            e1.printStackTrace();
        }
    }

}
