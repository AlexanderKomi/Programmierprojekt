package de.hsh.daniel.model;

import common.util.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;


/*
 Loads images from given image path into ArrayList
 */

public class ramImgLoader {

    private static final ArrayList<Image>   imgList     = new ArrayList<Image>();
    private static final String             imgLocation = "de/hsh/daniel/images/";
    JFXPanel jfxPanel = new JFXPanel();

    public ArrayList<Image> imgToList() {
        for(int i = 1; i <= 5 ; i++) {
            Image img = new Image(imgLocation+i+".png");
            imgList.add(img);
        }
        Logger.log("ImgList:" + imgList.toString());
        return imgList;
    }


    public ArrayList<Image> getImgList(){ return this.imgList;}
}