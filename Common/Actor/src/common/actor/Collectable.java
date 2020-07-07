/**
 * @author Julian Sender
 */
package common.actor;

import java.util.List;

/**
 * Abstract class as blueprint for all collectables
 */
abstract public class Collectable extends Actor {

    public static final String collected = "I am collected, remove me :)";
    private             Actor  collector = null;

    /**
     * Extending Actor and needing to give Actor the picture
     * Then following many overloadings of constructor
     * @param pictureFileName path of desired picture
     */
    protected Collectable( String pictureFileName ) {
        super( pictureFileName );
    }

    public Collectable( String pictureFileName, double x, double y ) {
        super( pictureFileName, x, y );
    }

    protected Collectable( List<String> pictureFilePaths, double x, double y, int delay ) {
        super( pictureFilePaths, x, y, delay );
    }

    protected Collectable( double x, double y, int delay, String... pictureFilePaths ) {
        super( x, y, delay, pictureFilePaths );
    }

    protected Collectable( double x, double y, int delay, double scale, String... pictureFilePaths ) {
        super( x, y, delay, pictureFilePaths );
        this.scaleImage( scale );
    }

    protected Collectable( double x, double y, double scale, String picturePath ) {
        super( x, y, scale, picturePath );
        this.scaleImage( scale );
    }

    public Collectable( double x, double y, String invisiblePictures ) {this( invisiblePictures, x, y );}


    /**
     * Notifys ovservers if a collectable was collected
     * @param collector param for new collected to notify observers
     */
    public void wasCollected( Actor collector ) {
        this.collector = collector;
        this.setChanged();
        this.notifyObservers( collected );
    }

    /**
     * GETTER
     * @return Returns a collector-instance
     */
    public Actor getCollector() {
        return collector;
    }
}
