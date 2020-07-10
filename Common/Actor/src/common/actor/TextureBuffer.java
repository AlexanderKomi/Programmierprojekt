package common.actor;

import javafx.scene.image.Image;

import java.io.IOException;
import java.util.HashMap;

final class TextureBuffer {

    private static final HashMap<String, Image> fileToImage = new HashMap<>();

    static Image loadImage( final String fileName ) {
        if ( !fileToImage.containsKey( fileName ) ) {
            try {
                fileToImage.put(fileName, ImageLoader.loadImage(fileName ));
            } catch (NullPointerException | IOException npe ) {
                npe.printStackTrace();
            }
        }
        return fileToImage.get( fileName );
    }
}
