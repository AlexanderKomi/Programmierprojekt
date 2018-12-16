package de.hsh.daniel.model;


import common.actor.Drawable;
import javafx.scene.image.Image;


public class Card  {

    private int             id;
    private Image           image;
    private Image           cardBack = new Image("de/hsh/daniel/resources/0.png");
    private boolean         cardMatched = false;
    private boolean         cardSelected = false;



     Card(Image img, int id) {
        setImage(img);
        setId(id);
    }


    @Override
    public String toString() {
        return "id: " + this.getId();
    }



    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        if(id != other.id)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }


    public boolean isCardMatched(Card c1, Card c2) {
        if(c1.equals(c2)) {
            setCardMatched(true);
            //leave cards face up
        } else {
            setCardMatched(false);
            //flip cards back
        }
        return false;
    }


/* -------------------------------- GETTERS & SETTERS -------------------------------- */



    public Image getCardBack() {
        return cardBack;
    }
    public void setCardBack(Image cardBack) {
        this.cardBack = cardBack;
    }

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }


    public Image getImage() { return this.image; }
    public void setImage(Image image) {this.image = image; }

    public boolean getCardMatched() { return this.cardMatched; }
    public void setCardMatched(boolean cardMatched) { this.cardMatched = cardMatched; }

    public boolean getCardSelected() { return this.cardSelected; }
    public void setCardSelected(boolean cardSelected) { this.cardSelected = cardSelected; }
}

