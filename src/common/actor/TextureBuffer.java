package common.actor;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public final class TextureBuffer {

    private static final HashMap<String, Image> fileToImage = new HashMap<>();

    static void addFile( String filepath ) throws FileNotFoundException {
        fileToImage.put( filepath, new Image( new FileInputStream( filepath ) ) );
    }

    static Image getImage( String filepath ) {
        return fileToImage.get( filepath );
    }

    public static boolean contains( String fileName ) {
        return fileToImage.containsKey( fileName );
    }
}
