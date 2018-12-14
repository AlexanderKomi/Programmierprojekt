package common.util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public final class ImageLoader {

    public static Image loadImage( final String relativeFilePath ) {

        InputStream u = null;
        try {
            u = getInputStream( relativeFilePath );
        }
        catch ( NullPointerException npe ) {
            npe.printStackTrace();
        }
        assert u != null;
        BufferedImage image = null;
        try {
            image = ImageIO.read( u );
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        assert image != null;
        return SwingFXUtils.toFXImage( image, null );
    }

    public static InputStream getInputStream( String relativePath ) throws NullPointerException {
        InputStream inputStream = ImageLoader.class.getResourceAsStream( relativePath );
        if ( inputStream == null ) {
            throw new NullPointerException( "File does not exist : " + relativePath );
        }
        else {
            return inputStream;
        }
    }

}
