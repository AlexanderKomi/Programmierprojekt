package common.actor;

import java.util.List;

public class LevelElement extends Actor {

    protected LevelElement( String pictureFileName ) {
        super( pictureFileName );
    }

    public LevelElement( String pictureFileName, double x, double y ) {
        super( pictureFileName, x, y );
    }

    public LevelElement( String mustHavePath, String... pictureFilePaths ) {
        super( mustHavePath, pictureFilePaths );
    }

    public LevelElement( List<String> pictureFilePaths ) {
        super( pictureFilePaths );
    }

    public LevelElement( String[] pictureFilePaths ) {
        super( pictureFilePaths );
    }

    public LevelElement( List<String> pictureFilePaths, double x, double y, int delay ) {
        super( pictureFilePaths, x, y, delay );
    }

    public LevelElement( double x, double y, String... pictureFilePaths ) {
        super( x, y, 0, pictureFilePaths );
    }

    public LevelElement( double x, double y, int delay, String... pictureFilePaths ) {
        super( x, y, delay, pictureFilePaths );
    }
}
