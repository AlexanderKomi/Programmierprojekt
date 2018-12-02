package de.hsh.daniel;

import java.awt.Dimension;
import javax.swing.JFrame;


public class Memory
{
    public static void main(String... args){
        Board b = new Board();
        b.setPreferredSize(new Dimension(1200,800)); //need to use this instead of setSize
        b.setLocation(500, 250);
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.pack();
        b.setVisible(true);
    }
}