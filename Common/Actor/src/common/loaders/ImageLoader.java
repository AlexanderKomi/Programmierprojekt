/**
 * @author Julian Sender
 */
package common.loaders;

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
    public static Image loadImage( final String relativeFilePath ) throws IOException {

        InputStream u = null;
        try {
            u = getInputStream( relativeFilePath );
            BufferedImage image = ImageIO.read( u );
            assert image != null;
            return SwingFXUtils.toFXImage( image, null );
        } finally {
            if (u != null) {
                try {
                    u.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * Gets Inputstream of resources as Stream for pictures depending on relative path to picture
     * @param relativePath relative path to the picture
     * @return returns an inputstream containing the image
     * @throws NullPointerException Location not found
     */
    private static InputStream getInputStream( String relativePath ) throws NullPointerException,
            FileNotFoundException {
        java.net.URL x = ImageLoader.class.getResource(relativePath);
        if (x != null) {
            File f = new File(x.getPath());
            if (f.exists()) {
                return ImageLoader.class.getResourceAsStream( relativePath );
            }
            throw new FileNotFoundException("File does not exist: " + x.getPath());
        }
        throw new FileNotFoundException("File does not exist under relative Path: " + relativePath);
    }

}
