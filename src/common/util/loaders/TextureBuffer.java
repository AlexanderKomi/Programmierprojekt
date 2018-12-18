package common.util.loaders;

import javafx.scene.image.Image;

import java.util.HashMap;

public final class TextureBuffer {

    private static final HashMap<String, Image> fileToImage = new HashMap<>();

    private static void addFile( final String filepath ) {
        try {
            fileToImage.put( filepath,
                             ImageLoader.loadImage( filepath ) );
        }
        catch ( NullPointerException npe ) {
            npe.printStackTrace();
        }
    }

    private static Image getImage( final String filepath ) {
        return fileToImage.get( filepath );
    }

    private static boolean contains( final String fileName ) {
        return fileToImage.containsKey( fileName );
    }

    public static Image loadImage( final String fileName ) {
        if ( !TextureBuffer.contains( fileName ) ) {
            TextureBuffer.addFile( fileName );
        }
        return TextureBuffer.getImage( fileName );
    }
}
