package common.actor;

import java.util.List;

abstract public class Collectable extends Actor {

    public static final String collected = "I am collected, remove me :)";
    private             Actor  collector = null;

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

    public void wasCollected( Actor collector ) {
        this.collector = collector;
        this.setChanged();
        this.notifyObservers( collected );
    }

    public Actor getCollector() {
        return collector;
    }
}
