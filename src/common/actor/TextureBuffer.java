package common.actor;

import common.util.ImageLoader;
import javafx.scene.image.Image;

import java.util.HashMap;

final class TextureBuffer {

    private static final HashMap<String, Image> fileToImage = new HashMap<>();

    private static void addFile( final String filepath ) {
        fileToImage.put( filepath,
                         ImageLoader.loadImage( filepath ) );
    }

    private static Image getImage( final String filepath ) {
        return fileToImage.get( filepath );
    }

    private static boolean contains( final String fileName ) {
        return fileToImage.containsKey( fileName );
    }

    static Image loadImage( final String fileName ) {
        if ( !TextureBuffer.contains( fileName ) ) {
            TextureBuffer.addFile( fileName );
        }
        return TextureBuffer.getImage( fileName );
    }
}
