package de.hsh.daniel.model;


import common.actor.Level;
import common.util.Logger;
import de.hsh.alexander.src.actor.collectables.DataCoin;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;


/**
 * Class represents Gameboard where Cards are laid out
 */

public class Board  {

    private                 ArrayList<Card>         cardList        = new ArrayList<>();
    public static int                               numberOfPairs   = 0;

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

