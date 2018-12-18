package de.hsh.daniel.model;


import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Class represents Gameboard where Cards are laid out
 */
public class Board  {

    private       ArrayList<Card> cardList      = new ArrayList<>();
    public static int             numberOfPairs = 0; // These are set externally from the menu.

    Board() { initCards( numberOfPairs );}

    private void initCards(final int numberOfPairs) {
        for (int i = 0; i < numberOfPairs; i++) {
            Card c1 = new Card(Resources.cardImages[i], i);
            Card c2 = new Card(Resources.cardImages[i], i);
            cardList.add(c1);
            cardList.add(c2);
        }
        Collections.shuffle(cardList);
    }

    static void drawGrid( Canvas canvas, Board board, double gridW, double gridH, double imgSize, int numberOfPairs ) {

        int spacing = 40;
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
        int imgCount = iterate( board, canvas, xStart, xStartReset, spacing, imgSize, gridW, gridH );
    }

    private static int iterate( Board board,
                                Canvas canvas,
                                int xStart,
                                final int xStartReset,
                                final int spacing,
                                final double imgSize,
                                final double gridW,
                                final double gridH ) {
        int yStart   = 10;
        int imgCount = 0;

        for ( int j = 0 ; j < gridH ; j++ ) {
            for ( int k = 0 ; k < gridW ; k++ ) {
                drawCard( board, canvas, xStart, yStart, imgCount, imgSize );
                xStart += (imgSize + spacing);
                imgCount++;
            }
            yStart += imgSize + spacing / 2;
            xStart = xStartReset;
        }
        return imgCount;
    }

    private static void drawCard(
            Board board,
            Canvas canvas,
            final int xStart,
            final int yStart,
            final int imgCount,
            double imgSize ) {
        Card i = board.getCardList().get( imgCount );
        i.setPos( xStart, yStart );
        i.setWidth( imgSize );
        i.setHeight( imgSize );
        i.draw( canvas );
    }

    /* -------------------- GETTER & SETTER -------------------- */


    public ArrayList<Card> getCardList() {
        return cardList;
    }
}

