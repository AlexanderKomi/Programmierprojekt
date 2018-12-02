package de.hsh.daniel;

import javafx.scene.image.ImageView;

import javax.swing.*;


public class Card extends JButton {
    private int id;
    private boolean matched = false;



    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }


    public void setMatched(boolean matched){
        this.matched = matched;
    }

    public boolean getMatched(){
        return this.matched;
    }
}
