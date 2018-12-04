package common.actor;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public class Drawable extends Observable {

    private double x;
    private double y;

    private double height;
    private double width;

    private String name;

    private int     switchingBuffer          = 0;
    private int     switchingDelay           = 0;
    private boolean switchImageAutomatically = true;


    private Image            currentImage;
    private ArrayList<Image> images = new ArrayList<>();


    protected Drawable( String pictureFileName ) throws FileNotFoundException {
        this( pictureFileName, 0, 0 );
    }

    public Drawable( String pictureFileName, double x, double y ) throws FileNotFoundException {
        this.currentImage = loadPicture( pictureFileName );
        this.setHeight( this.getCurrentImage().getHeight() );
        this.setWidth( this.getCurrentImage().getWidth() );
        this.setX( x );
        this.setY( y );
    }

    protected Drawable( List<String> pictureFilePaths, double x, double y, int delay )
            throws FileNotFoundException {
        this.setPos( x, y );
        boolean heightIsSet = false;

        for ( String filePath : pictureFilePaths ) {
            this.images.add( loadPicture( filePath ) );
            if ( !heightIsSet ) {
                this.currentImage = this.images.get( 0 );
                this.setWidth( this.currentImage.getWidth() );
                this.setHeight( this.currentImage.getHeight() );
                heightIsSet = true;
            }
        }
        this.switchingDelay = delay;
    }

    private Image loadPicture( String fileName ) throws FileNotFoundException {
        this.name = fileName;
        return new Image( new FileInputStream( fileName ) );
    }

    /**
     * Switch images based on buffer implementation.
     */
    protected void switchImages() {
        if ( this.images.isEmpty() || !this.switchImageAutomatically ) {
            return;
        }
        if ( this.switchingBuffer < this.switchingDelay ) {
            this.switchingBuffer++;
            return;
        }
        switchToNextImage();
    }

    public void switchToNextImage() {
        this.switchingBuffer = 0;
        int index = this.images.indexOf( this.currentImage );
        if ( index < this.images.size() - 1 ) {
            this.currentImage = this.images.get( index + 1 );
        }
        else {
            this.currentImage = this.images.get( 0 );
        }
    }

    public void draw( Canvas canvas ) {
        draw( canvas, 0, 0 );
    }

    public void draw( Canvas canvas, double[] offset_pos ) {
        draw( canvas, offset_pos[ 0 ], offset_pos[ 1 ] );
    }

    public void draw( Canvas canvas, double offset_to_new_x, double offset_to_new_y ) {
        boolean[] isInBounds    = isInBounds( canvas, offset_to_new_x, offset_to_new_y );
        double[]  in_bounds_pos = calcPosAfterBounds( isInBounds, offset_to_new_x, offset_to_new_y );
        double[]  old_pos       = this.getPos();
        this.setPos( in_bounds_pos );
        this.setPos( beforeDrawing( old_pos, in_bounds_pos ) ); // Maybe reset ? :)
        switchImages();
        canvas.getGraphicsContext2D().drawImage( this.currentImage, this.x, this.y, this.width, this.height );
    }

    protected double[] beforeDrawing( double[] current_pos, double[] new_pos ) {
        return new_pos;
    }

    double[] calcPosAfterBounds( boolean[] isInBounds, double new_x, double new_y ) {
        double[] temp = new double[] {
                this.x, this.y
        };

        if ( isInBounds[ 0 ] ) {
            temp[ 0 ] += (new_x);
        }
        if ( isInBounds[ 1 ] ) {
            temp[ 1 ] += (new_y);
        }
        return temp;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj instanceof Drawable ) {
            Drawable d = (Drawable) obj;
            return Arrays.equals( this.getPos(), d.getPos() ) &&
                   this.getHeight() == d.getHeight() &&
                   this.getWidth() == d.getWidth() &&
                   this.getName().equals( d.getName() ) &&
                   this.currentImage.equals( d.currentImage )
                    ;
        }
        return false;
    }

    @Override
    public String toString() {
        String result = this.getClass() + "(";
        result += prepareToString();
        return result + ")";
    }

    public String prepareToString() {
        String result = "name:" + this.getName() + ", ";
        result += "x:" + this.getX() + ", ";
        result += "y:" + this.getY() + ", ";
        result += "width:" + this.getWidth() + ", ";
        result += "height:" + this.getHeight();
        return result;
    }

    /**
     * Returns an boolean Array with index 0 equals x coordinate and
     * index 1 equals y coordinate
     *
     * @author Alex
     * @author Kevin
     */
    boolean[] isInBounds( Canvas canvas, double new_x, double new_y ) {
        return CollisionCheck.isInBounds( this, canvas, new_x, new_y );
    }

    private void movePos( double horizontal, double vertical ) {
        this.setPos(
                this.getX() + horizontal,
                this.getY() + vertical
                   );
    }

    public void setPos( double x, double y ) {
        this.setX( x );
        this.setY( y );
    }

    public void setPos( double[] pos ) {
        this.setX( pos[ 0 ] );
        this.setY( pos[ 1 ] );
    }

    public double[] getPos() {
        return new double[] { this.getX(), this.getY() };
    }

    public double getX() {
        return x;
    }

    public void setX( double x ) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY( double y ) {
        this.y = y;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight( double height ) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth( double width ) {
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public int getSwitchingBuffer() {
        return switchingBuffer;
    }

    public void setSwitchingBuffer( int switchingBuffer ) {
        this.switchingBuffer = switchingBuffer;
    }

    public int getSwitchingDelay() {
        return switchingDelay;
    }

    public void setSwitchingDelay( int switchingDelay ) {
        this.switchingDelay = switchingDelay;
    }

    public boolean isSwitchImageAutomatically() {
        return switchImageAutomatically;
    }

    public void setSwitchImageAutomatically( boolean switchImageAutomatically ) {
        this.switchImageAutomatically = switchImageAutomatically;
    }

    public Image getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage( Image currentImage ) {
        this.currentImage = currentImage;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages( ArrayList<Image> images ) {
        this.images = images;
    }
}
