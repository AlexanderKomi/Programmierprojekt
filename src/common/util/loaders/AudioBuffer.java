package common.util.loaders;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;

import java.io.File;
import java.util.HashMap;

public final class AudioBuffer {

    private static final HashMap<String, Media> fileToMedia = new HashMap<>();

    private static void addFile( final String filepath ) throws MediaException {
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

    private static Media newMedia( final String fileName ) throws MediaException {
        return new Media( new File( fileName ).toURI().toString() );
    }

    private static boolean contains( final String fileName ) {
        return fileToMedia.containsKey( fileName );
    }

    public static Media loadMedia( final String fileName ) throws MediaException {
        if ( !AudioBuffer.contains( fileName ) ) {
            AudioBuffer.addFile( fileName );
        }
        return fileToMedia.get( fileName );
    }

}
