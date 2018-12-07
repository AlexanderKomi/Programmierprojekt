package de.hsh.daniel.model;

import common.actor.Actor;
import common.util.Logger;
import javafx.scene.image.Image;

import java.util.ArrayList;


public class Card extends Actor {

    private int                             id;
    private Image                           image;
    private static final Image              cardBackImg = new Image("de/hsh/daniel/images/back.png");
    private boolean                         cardMatched;
    private boolean                         cardSelected;


    public Card(String pictureFileName) {
        super(pictureFileName);
        this.id += 1;
    }




    @Override
    public String toString() {
        return "id: " + this.getId();
    }


    public boolean isMatched() {
        return cardMatched == true;
    }

    public boolean isCardSelected() {
        return cardSelected == true;
    }

    /* -------------------------------- GETTERS & SETTERS -------------------------------- */



    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }


    public Image getImage() { return this.image; }
    public void setImage(Image image) {this.image = image; }

    public boolean getCardMatched() { return this.cardMatched; }
    public void setCardMatched(boolean cardMatched) { this.cardMatched = cardMatched; }

    public boolean getCardSelected() { return this.cardSelected; }
    public void setCardSelected(boolean cardSelected) { this.cardSelected = cardSelected; }
}
