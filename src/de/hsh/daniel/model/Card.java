package de.hsh.daniel.model;

import javafx.scene.image.Image;


public class Card {

    private int                             id;
    private Image                           image;
    private boolean                         cardMatched;
    private boolean                         cardSelected;


    public Card(Image img, int id) {
        this.image = img;
        this.id = id;
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
