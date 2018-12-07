package de.hsh.daniel.model;

import common.util.Logger;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Class represents Gameboard where Cards are laid out
 */
public class Board  {

    private GridPane            gridPane     = new GridPane();
//    private Scene               scene        = new Scene(gridPane, 1200,800);
    private ArrayList<Card>     cardList     = new ArrayList<Card>();

    //TODO: Get number of pairs from MenuButton
    private int numberOfPairs;


    public void intiCardsNew(int numberOfPairs) {
        for (int i = 0; i<numberOfPairs; i++){
            cardList.add(i, new Card(i));
            cardList.add((i+1), new Card(i));

        }
        Logger.log(cardList.toString());
        Collections.shuffle(cardList);
    }
}

