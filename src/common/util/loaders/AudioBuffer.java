package common.util.loaders;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;

import java.net.URISyntaxException;
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
        try {
            return new Media( AudioBuffer.class.getResource( fileName ).toURI().toString() );
        }
        catch ( URISyntaxException e ) {
            e.printStackTrace();
        }
        return null;
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
