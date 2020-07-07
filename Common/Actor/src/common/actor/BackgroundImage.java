package common.actor;

public class BackgroundImage extends Drawable {

    private static final String fallbackPath = "/common/gui/xp_background_1280x800.jpg";

    BackgroundImage() {
        super( fallbackPath );
    }
}
