package de.hsh.daniel.images;

import java.io.File;
import java.util.ArrayList;

public class ImageLoader {

    private File imgDir = new File("/daniel/images/");
    private ArrayList<File> imgList = new ArrayList<File>();

    public ArrayList<File> loadImages() {
        for (final File imgFile : imgDir.listFiles()) {
            imgList.add(imgFile);
        }
        System.out.println(imgList.toString());
        return imgList;
    }


}
