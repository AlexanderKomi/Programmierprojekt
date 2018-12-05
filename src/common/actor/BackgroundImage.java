package common.actor;

import common.util.Path;

import java.util.List;

public class BackgroundImage extends Drawable {

    public static final String fallbackPath = Path.getExecutionLocation() + "common/gui/xp_background_1280x800.jpg";

    public BackgroundImage() {
        super( fallbackPath );
    }

    public BackgroundImage( String pictureFileName ) {
        super( pictureFileName );
    }

    public BackgroundImage( String pictureFile, String... pictureFilePaths ) {
        super( pictureFile, pictureFilePaths );
    }

    public BackgroundImage( List<String> pictureFilePaths ) {
        super( pictureFilePaths );
    }

    public BackgroundImage( List<String> pictureFilePaths, int delay ) {
        super( pictureFilePaths, delay );
    }

    public BackgroundImage( int delay, String... pictureFilePaths ) {
        super( 0, 0, delay, pictureFilePaths );
    }
}
