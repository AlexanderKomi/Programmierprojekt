package de.hsh.daniel.model;


import common.actor.BackgroundImage;
import common.actor.Drawable;
import common.config.WindowConfig;
import common.util.Logger;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Class represents Gameboard where Cards are laid out
 */

public class Board  {

    private ArrayList<Card> cardList = new ArrayList<>();
    private ArrayList<Image> imgList = new ArrayList<>();
    private ImageView       cardView = new ImageView();
    private static final String imgLocation = "de/hsh/daniel/resources/";
    private int numberOfPairs = 0;


    public void turnCard(int id) {
       if(id == (-1)){
           cardView.setImage(cardList.get(1).getCardBack());
       } else {
        cardView.setImage(getCardList().get(id).getImage());
        }
    }

    public ArrayList<Image> imgToList() {
        if (numberOfPairs == 0) {
            Logger.log("Number of pairs must be > 0!");
        } else {
            for (int i = 1; i <= numberOfPairs; i++) {
                Image img = new Image(imgLocation + i + ".png");
                imgList.add(img);
            }
        }
        return imgList;
    }


    public ArrayList<Card> initCards(int numberOfPairs) {
        for (int i = 1; i <= numberOfPairs; i++) {
            Card c1 = new Card(imgList.get((i-1)), (i-1));
            Card c2 = new Card(imgList.get((i-1)), (i-1));
            cardList.add((i-1), c1);
            cardList.add((i), c2);
        }
        Collections.shuffle(cardList);
        return cardList;
    }




    /* -------------------- GETTER & SETTER -------------------- */


    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public void setCardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }


    public ArrayList<Image> getImgList() {
        return imgList;
    }

    public void setImgList(ArrayList<Image> imgList) {
        this.imgList = imgList;
    }

    public static String getImgLocation() {
        return imgLocation;
    }

    public int getNumberOfPairs() {
        return numberOfPairs;
    }

    public void setNumberOfPairs(int numberOfPairs) {
        this.numberOfPairs = numberOfPairs;
    }

}

