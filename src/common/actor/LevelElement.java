package common.actor;

import java.util.List;

abstract public class LevelElement extends Actor {

    protected LevelElement( final String pictureFileName ) {
        super( pictureFileName );
    }

    public LevelElement( final String pictureFileName, final double x, final double y ) {
        super( pictureFileName, x, y );
    }

    public LevelElement( final String mustHavePath, final String... pictureFilePaths ) {
        super( mustHavePath, pictureFilePaths );
    }

    public LevelElement( final List<String> pictureFilePaths ) {
        super( pictureFilePaths );
    }

    public LevelElement( final String[] pictureFilePaths ) {
        super( pictureFilePaths );
    }

    public LevelElement( final List<String> pictureFilePaths, final double x, final double y, final int delay ) {
        super( pictureFilePaths, x, y, delay );
    }

    public LevelElement( final double x, final double y, final String... pictureFilePaths ) {
        super( x, y, 0, pictureFilePaths );
    }

    public LevelElement( final double x, final double y, final int delay, final String... pictureFilePaths ) {
        super( x, y, delay, pictureFilePaths );
    }

    protected LevelElement( double x, double y, String picturePath, double scale ) {
        super( x, y, scale, picturePath );
        this.scaleImage( scale );
    }
}
