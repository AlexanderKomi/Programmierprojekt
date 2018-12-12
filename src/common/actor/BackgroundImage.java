package common.actor;

import java.util.List;

public class BackgroundImage extends Drawable {

    public static final String fallbackPath = "/common/gui/xp_background_1280x800.jpg";

    public BackgroundImage() {
        super( fallbackPath );
    }

    public BackgroundImage( final String pictureFileName ) {
        super( pictureFileName );
    }

    public BackgroundImage( final String pictureFile, final String... pictureFilePaths ) {
        super( pictureFile, pictureFilePaths );
    }

    public BackgroundImage( final List<String> pictureFilePaths ) {
        super( pictureFilePaths );
    }

    public BackgroundImage( final List<String> pictureFilePaths, final int delay ) {
        super( pictureFilePaths, delay );
    }

    public BackgroundImage( final int delay, final String... pictureFilePaths ) {
        super( 0, 0, delay, pictureFilePaths );
    }
}
