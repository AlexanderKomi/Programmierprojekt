package de.hsh.daniel.model;

import common.util.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;


import java.io.File;
import java.util.ArrayList;


/*
 Loads images from given image path into ArrayList
 */

public class ramImgLoader {

    private static final ArrayList<Image>   imgList     = new ArrayList<Image>();
    private static final String             imgLocation = "de/hsh/daniel/images/";
    private int                             fileCount;
    JFXPanel jfxPanel = new JFXPanel();

    public ArrayList<Image> imgToList() {
        fileCount = countImageFiles();
        for(int i = 1; i < (fileCount) ; i++) {
            Image img = new Image(imgLocation+i+".png");
            imgList.add(img);
        }
        Logger.log("ImgList:" + imgList.toString());
        return imgList;
    }

    private int countImageFiles() {
        File f = new File("C:\\Users\\danie\\Desktop\\HsH\\workspaces\\Programmierprojekt\\src\\de\\hsh\\daniel\\images");
        File[] files = f.listFiles();

        if(files != null) {
            for (int i = 0; i < files.length; i++) {
                fileCount++;
                File file = files[i];
            }
        }

    Logger.log("Files in path: "+fileCount);
    return fileCount;
    }

    public ArrayList<Image> getImgList(){ return this.imgList;}
}
