package common.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;

import java.io.File;
import java.io.FileNotFoundException;
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
        catch (NullPointerException | FileNotFoundException npe ) {
            npe.printStackTrace();
        }
    }

    private static Media newMedia( final String fileName ) throws MediaException, FileNotFoundException {
        File soundFile = new File( fileName );
        if (soundFile.exists()) {
            return new Media( soundFile.toURI().toString() );
        }
        throw new FileNotFoundException(fileName);
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
