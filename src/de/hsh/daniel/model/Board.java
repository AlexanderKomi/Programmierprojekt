package de.hsh.daniel.model;

import common.util.Logger;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Class represents Gameboard where Cards are laid out
 */
public class Board  {

    private ArrayList<Card>         cardList     = new ArrayList<Card>();
    private ramImgLoader            imgLoader    = new ramImgLoader();
    private ArrayList<Image>        imgList      = new ArrayList<>();
    private static final String     imgLocation  = "de/hsh/daniel/images/";

    //TODO: Get number of pairs from MenuButton
    private int numberOfPairs;


    public void initCards(int numberOfPairs) {
        for (int i = 0; i<numberOfPairs; i++){
            cardList.add(i, new Card(imgList.get(i).toString()));
            cardList.add((i+1), new Card(imgList.get(i).toString()));
        }
        Logger.log(cardList.toString());

        imgLoader.imgToList();
        imgList = imgLoader.getImgList();

        for(Card c : cardList) {
            for (int i = 1; i < imgList.size(); i++) {
                c.setImage(imgList.get(i));
            }
        }
        Collections.shuffle(cardList);
        Logger.log(cardList);
    }
}

