package common.util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public final class ImageLoader {

    public static Image loadImage( final String relativeFilePath ) {
        final InputStream u = ImageLoader.class.getResourceAsStream( relativeFilePath );
        if ( u == null ) {
            throw new NullPointerException( "Image does not exist : " + relativeFilePath );
        }
        BufferedImage i = null;
        try {
            i = ImageIO.read( u );
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        assert i != null;
        return SwingFXUtils.toFXImage( i, null );
    }

}
