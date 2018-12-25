package common.util.loaders;

import javafx.scene.media.Media;

import java.io.File;
import java.util.HashMap;

public final class AudioBuffer {

    private static final HashMap<String, Media> fileToMedia = new HashMap<>();

    private static void addFile( final String filepath ) {
        try {
            fileToMedia.put(
                    filepath,
                    newMedia( filepath )
                           );
        }
        catch ( NullPointerException npe ) {
            npe.printStackTrace();
        }
    }

    private static Media newMedia( final String fileName ) {
        //String path = AudioBuffer.class.getResource( fileName ).getPath(); // new way
        String path = new File( fileName ).toURI().toString(); // Old way of getting a file
        return new Media( path );
    }

    private static boolean contains( final String fileName ) {
        return fileToMedia.containsKey( fileName );
    }

    public static Media loadMedia( final String fileName ) {
        if ( !AudioBuffer.contains( fileName ) ) {
            AudioBuffer.addFile( fileName );
        }
        return fileToMedia.get( fileName );
    }

}
