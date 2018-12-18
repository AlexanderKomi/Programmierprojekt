package de.hsh.daniel.model;


import java.util.ArrayList;
import java.util.Collections;


/**
 * Class represents Gameboard where Cards are laid out
 */

public class Board  {

    private                 ArrayList<Card>         cardList        = new ArrayList<>();
    public static int                                     numberOfPairs   = 0;

    Board() {
        initCards(numberOfPairs);
    }

    private void initCards(final int numberOfPairs) {
        for (int i = 0; i < numberOfPairs; i++) {
            Card c1 = new Card(Resources.cardImages[i], i);
            Card c2 = new Card(Resources.cardImages[i], i);
            cardList.add(c1);
            cardList.add(c2);
        }
        Collections.shuffle(cardList);
    }

    /* -------------------- GETTER & SETTER -------------------- */


    public ArrayList<Card> getCardList() {
        return cardList;
    }

}

