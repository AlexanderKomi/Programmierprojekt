package de.hsh.daniel.model;


import common.actor.Actor;
import javafx.scene.image.Image;


public class Card extends Actor {

    private final int pair_id;
    private boolean cardMatched = false;
    private boolean cardSelected = false;

    Card(String pictureFileName, int pair_id) {
        super(pictureFileName);
        this.setSwitchImageAutomatically(false);
        this.addSwitchingImage(Resources.cardback);
        this.setCurrentImage(Resources.cardback);
        this.pair_id = pair_id;
    }



    @Override
    public String toString() {
        return "pair_id: " + this.getPair_id();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        if (pair_id != other.pair_id)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + pair_id;
        return result;
    }


    public boolean isCardMatched(Card c1, Card c2) {
        if (c1.equals(c2)) {
            setCardMatched(true);
            //leave cards face up
        } else {
            setCardMatched(false);
            //flip cards back
        }
        return false;
    }


    /* -------------------------------- GETTERS & SETTERS -------------------------------- */


    public int getPair_id() {
        return this.pair_id;
    }

    public boolean getCardMatched() {
        return this.cardMatched;
    }

    public void setCardMatched(boolean cardMatched) {
        this.cardMatched = cardMatched;
    }

    public boolean getCardSelected() {
        return this.cardSelected;
    }

    public void setCardSelected(boolean cardSelected) {
        this.cardSelected = cardSelected;
    }
}

