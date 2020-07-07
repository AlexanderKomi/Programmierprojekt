package common.actor;

abstract public class LevelElement extends Actor {

    public LevelElement( final double x, final double y, final String... pictureFilePaths ) {
        super( x, y, 0, pictureFilePaths );
    }

    protected LevelElement( double x, double y, String picturePath, double scale ) {
        super( x, y, scale, picturePath );
        this.scaleImage( scale );
    }
}
