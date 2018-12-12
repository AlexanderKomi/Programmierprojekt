package common.actor;

import common.util.ImageLoader;
import javafx.scene.image.Image;

import java.util.HashMap;

final class TextureBuffer {

    private static final HashMap<String, Image> fileToImage = new HashMap<>();

    static void addFile( final String filepath ) {
        fileToImage.put( filepath,
                         ImageLoader.loadImage( filepath ) );
    }

    static Image getImage( final String filepath ) {
        return fileToImage.get( filepath );
    }

    static boolean contains( final String fileName ) {
        return fileToImage.containsKey( fileName );
    }
}
