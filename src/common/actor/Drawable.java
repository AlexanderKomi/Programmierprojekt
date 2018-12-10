package common.actor;

import javafx.application.Platform;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Drawable is an image with bounds checking.
 * Also support Sprites, scaling and rotation.
 * <p>
 * Use the constructors as you like :)
 * Its easier to support multiple projects, when many constructors are given as an interface.
 * <p>
 * Every Drawable can be drawn on a canvas.
 */
abstract public class Drawable extends Observable {

    private static int id_counter = 0;
    public         int id;

    private double x;
    private double y;

    private double height;
    private double width;

    private String name;

    private int     switchingBuffer          = 0;
    private int     switchingDelay           = 0;
    private boolean switchImageAutomatically = true;
    private double  scaleX                   = 1.0;
    private double  scaleY                   = 1.0;

    private ImageView        imageView       = new ImageView();
    private Image            currentImage;
    private ArrayList<Image> switchingImages = new ArrayList<>();


    public Drawable( String pictureFileName ) {
        this( pictureFileName, 0, 0 );
    }

    public Drawable( String pictureFileName, double x, double y ) {
        this.setCurrentImage( loadPicture( pictureFileName ) );
        this.setHeight( this.getCurrentImage().getHeight() );
        this.setWidth( this.getCurrentImage().getWidth() );
        this.setX( x );
        this.setY( y );
        this.id = id_counter;
        id_counter++;
    }

    public Drawable( String pictureFile, String... pictureFilePaths ) {
        this( pictureFile, Arrays.asList( pictureFilePaths ), 0, 0, 0 );
    }

    public Drawable( String[] pictureFilePaths ) {
        this( 0, 0, 0 );
        this.setSwitchingImages( Arrays.asList( pictureFilePaths ) );
    }

    public Drawable( List<String> pictureFilePaths ) {
        this( pictureFilePaths, 0, 0, 0 );
    }

    public Drawable( List<String> pictureFilePaths, int delay ) {
        this( pictureFilePaths, 0, 0, delay );
    }

    public Drawable( List<String> pictureFilePaths, double x, double y ) {
        this( pictureFilePaths, x, y, 0 );
    }

    public Drawable( List<String> pictureFilePaths, double x, double y, int delay ) {
        this( x, y, delay );
        this.setSwitchingImages( pictureFilePaths );
        this.id = id_counter;
        id_counter++;
    }

    public Drawable( double x, double y, int delay, String mustHave, String... pictureFilePaths ) {
        this( Arrays.asList( pictureFilePaths ), x, y, delay );
        this.addSwitchingImage( mustHave );
    }

    public Drawable( Drawable d ) {
        this.setCurrentImage( d.getCurrentImage() );
        this.name = d.getName();
        this.setPos( d.getPos() );
        this.height = d.getHeight();
        this.width = d.getWidth();
        this.id = d.id;
    }

    public Drawable( String mustHave, List<String> asList, double x, double y, int delay ) {
        this( asList, x, y, delay );
        this.addSwitchingImage( mustHave );
    }

    private Drawable( double x, double y, int delay ) {
        this.setX( x );
        this.setY( y );
        this.setSwitchingDelay( delay );
    }

    public Drawable( double x, double y, int delay, String[] pictureFileName ) {
        this( Arrays.asList( pictureFileName ), x, y, delay );
    }

    private Image loadPicture( String fileName ) {
        this.name = fileName;
        if ( !TextureBuffer.contains( fileName ) ) {
            try {
                TextureBuffer.addFile( fileName );
            }
            catch ( FileNotFoundException e ) {
                e.printStackTrace();
            }
        }
        return TextureBuffer.getImage( fileName );
    }

    /**
     * Switch switchingImages based on buffer implementation.
     */
    protected void switchImages() {
        if ( this.switchingImages.isEmpty() || !this.switchImageAutomatically ) {
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
        int index = this.switchingImages.indexOf( this.currentImage );
        if ( index < this.switchingImages.size() - 1 ) {
            this.setCurrentImage( this.switchingImages.get( index + 1 ) );
        }
        else {
            this.setCurrentImage( this.switchingImages.get( 0 ) );
        }
    }

    public void scaleImageWidth( double factor ) {
        this.scaleX *= factor;
        this.width *= factor;
        this.imageView.setFitWidth( scaleX );
        this.imageView.setScaleX( scaleX );
    }

    public void scaleImageHeight( double factor ) {
        this.scaleY *= factor;
        this.height *= factor;
        this.imageView.setFitHeight( scaleY );
        this.imageView.setScaleY( scaleY );
    }

    public void scaleImage( double factor ) {
        scaleImageHeight( factor );
        scaleImageWidth( factor );
    }

    public void draw( Canvas canvas ) {
        draw( canvas, 0, 0 );
    }

    public void draw( Canvas canvas, double[] offset_pos ) {
        draw( canvas, offset_pos[ 0 ], offset_pos[ 1 ] );
    }

    public void draw( Canvas canvas, double offset_to_new_x, double offset_to_new_y ) {
        draw( canvas.getGraphicsContext2D(), canvas.getWidth(), canvas.getHeight(), offset_to_new_x, offset_to_new_y );
    }

    public void draw( GraphicsContext canvas,
                      double canvas_width,
                      double canvas_height,
                      double offset_to_new_x,
                      double offset_to_new_y ) {

        boolean[] isInBounds = CollisionCheck.isInBounds( this.getX(),
                                                          this.getY(),
                                                          this.getWidth(),
                                                          this.getHeight(),
                                                          canvas_width,
                                                          canvas_height,
                                                          offset_to_new_x,
                                                          offset_to_new_y );
        double[] in_bounds_pos = calcPosAfterBounds( isInBounds, offset_to_new_x, offset_to_new_y );
        double[] old_pos       = this.getPos();
        this.setPos( in_bounds_pos );
        this.setPos( beforeDrawing( old_pos, in_bounds_pos ) ); // Maybe reset ? :)
        switchImages();
        canvas.drawImage( this.currentImage, this.x, this.y, this.width, this.height );
    }

    public void draw( GraphicsContext gc ) {
        gc.drawImage( this.currentImage, this.x, this.y, this.width, this.height );
    }

    protected double[] beforeDrawing( double[] current_pos, double[] new_pos ) {
        return new_pos;
    }

    private double[] calcPosAfterBounds( boolean[] isInBounds, double new_x, double new_y ) {
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

    public void rotate( double rotate ) {

        try {
            Platform.runLater( () -> {
                this.imageView.setRotate( rotate );
                SnapshotParameters params = new SnapshotParameters();
                params.setFill( Color.TRANSPARENT );
                Image temp = this.imageView.snapshot( params, (WritableImage) this.imageView.getImage() );
                this.setCurrentImage( temp );
            } );
        }
        catch ( IllegalArgumentException | IllegalStateException iae ) {
            iae.printStackTrace();
            System.exit( 1 );
        }
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

    private String prepareToString() {
        String result = "name:" + this.getName() + ", ";
        result += "x:" + this.getX() + ", ";
        result += "y:" + this.getY() + ", ";
        result += "width:" + this.getWidth() + ", ";
        result += "height:" + this.getHeight();
        return result;
    }

    public void onClick() {
        this.setChanged();
        this.notifyObservers( this.getClass() + ": clicked" );
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

    public double[] getBounds() {
        return new double[] {
                this.getX(),
                this.getY(),
                this.getX() + this.getWidth(),
                this.getY() + this.getHeight()
        };
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
        //this.imageView.setSmooth( false );
        this.currentImage = currentImage;
        this.imageView.setImage( this.currentImage );
    }

    public void setCurrentImage( String filePath ) {
        this.currentImage = loadPicture( filePath );
    }

    public ArrayList<Image> getSwitchingImages() {
        return switchingImages;
    }

    public void setSwitchingImages( LinkedList<Image> switchingImages ) {
        this.switchingImages.addAll( switchingImages );
    }

    public void addSwitchingImage( String imagePath ) {
        this.getSwitchingImages().add( this.loadPicture( imagePath ) );
    }

    public void setSwitchingImages( List<String> imagePaths ) {
        boolean heightIsSet = false;

        for ( String filePath : imagePaths ) {
            this.switchingImages.add( loadPicture( filePath ) );
            if ( !heightIsSet ) {
                this.setCurrentImage( this.switchingImages.get( 0 ) );
                this.setWidth( this.currentImage.getWidth() );
                this.setHeight( this.currentImage.getHeight() );
                heightIsSet = true;
            }
        }
    }
}
