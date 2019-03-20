/**
 * @author Julian Sender
 */
package common.util.loaders;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * loading image
 */
public final class ImageLoader {

    /**
     * Returns Gets a path and returns image
     * @param relativeFilePath the relative path to the image
     * @return returns imagepath for SwingFXUtils
     */
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

    /**
     *
     * Gets Inputstream of resources as Stream for pictures depending on relative path to picture
     * @param relativePath relative path to the picture
     * @return returns an inputstream containing the image
     * @throws NullPointerException Location not found
     */
    private static InputStream getInputStream( String relativePath ) throws NullPointerException {
        InputStream inputStream = ImageLoader.class.getResourceAsStream( relativePath );
        if ( inputStream == null ) {
            throw new NullPointerException( "File does not exist : " + relativePath );
        }
        else {
            return inputStream;
        }
    }

    /**
     *
     * @param relativePath relative path to image
     * @return returns bufferedinputstream as fileinputstream depending on relative path
     * @throws NullPointerException Path not found
     */
    static BufferedInputStream getBufferedInputStream( String relativePath ) throws NullPointerException {
        try {
            return new BufferedInputStream( new FileInputStream( new File( relativePath ) ) );
        }
        catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }
        throw new NullPointerException( "File does not exist : " + relativePath );
    }

}
