package common.actor;

import java.io.FileNotFoundException;
import java.util.List;

public class LevelElement extends Actor {

    protected LevelElement( String pictureFileName ) throws FileNotFoundException {
        super( pictureFileName );
    }

    public LevelElement( String pictureFileName, double x, double y ) throws FileNotFoundException {
        super( pictureFileName, x, y );
    }

    public LevelElement( String... pictureFilePaths ) throws FileNotFoundException {
        super( pictureFilePaths );
    }

    public LevelElement( List<String> pictureFilePaths ) throws FileNotFoundException {
        super( pictureFilePaths );
    }

    public LevelElement( List<String> pictureFilePaths, double x, double y, int delay ) throws FileNotFoundException {
        super( pictureFilePaths, x, y, delay );
    }

    public LevelElement( double x, double y, String... pictureFilePaths ) throws FileNotFoundException {
        super( x, y, 0, pictureFilePaths );
    }

    public LevelElement( double x, double y, int delay, String... pictureFilePaths ) throws FileNotFoundException {
        super( x, y, delay, pictureFilePaths );
    }
}
