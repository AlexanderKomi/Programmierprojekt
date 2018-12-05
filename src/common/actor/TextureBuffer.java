package common.actor;

import common.util.ImageLoader;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.util.HashMap;

final class TextureBuffer {

    private static final HashMap<String, Image> fileToImage = new HashMap<>();

    static void addFile( String filepath ) throws FileNotFoundException {
        fileToImage.put( filepath,
                         ImageLoader.loadImage( filepath ) );
    }

    static Image getImage( String filepath ) {
        return fileToImage.get( filepath );
    }

    static boolean contains( String fileName ) {
        return fileToImage.containsKey( fileName );
    }
}
