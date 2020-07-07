package common.actor;

import common.loaders.TextureBuffer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    public final   int id;

    private double x;
    private double y;
    private double height;
    private double width;

    private String name;

    private int     switchingBuffer          = 0;
    private double  switchingDelay           = 0;
    private boolean switchImageAutomatically = true;

    private double  scaleX                   = 1.0;
    private double  scaleY                   = 1.0;

    private final ImageView        imageView       = new ImageView();
    private final ArrayList<Image> switchingImages = new ArrayList<>();


    protected Drawable( final String pictureFileName ) {
        this( pictureFileName, 0, 0 );
    }

    protected Drawable( final String pictureFileName, final double x, final double y ) {
        this.setCurrentImage( loadPicture( pictureFileName ) );

        this.setHeight( this.getCurrentImage().getHeight() );
        this.setWidth( this.getCurrentImage().getWidth() );

        this.setX( x );
        this.setY( y );
        this.id = id_counter;
        id_counter++;
    }

    protected Drawable( final String pictureFile, final String... pictureFilePaths ) {
        this( pictureFile, Arrays.asList( pictureFilePaths ), 0, 0, 0 );
    }

    protected Drawable( final String[] pictureFilePaths ) {
        this( 0, 0, 0 );
        this.setSwitchingImages( Arrays.asList( pictureFilePaths ) );
    }

    protected Drawable( final List<String> pictureFilePaths ) {
        this( pictureFilePaths, 0, 0, 0 );
    }

    protected Drawable( final List<String> pictureFilePaths, final int delay ) {
        this( pictureFilePaths, 0, 0, delay );
    }

    protected Drawable( final List<String> pictureFilePaths, final double x, final double y ) {
        this( pictureFilePaths, x, y, 0 );
    }

    protected Drawable( final List<String> pictureFilePaths, final double x, final double y, final int delay ) {
        this( x, y, delay );
        this.setSwitchingImages( pictureFilePaths );
    }

    protected Drawable( final double x,
                        final double y,
                        final int delay,
                        final String mustHave,
                        final String... pictureFilePaths ) {
        this( Arrays.asList( pictureFilePaths ), x, y, delay );
        this.addSwitchingImage( mustHave );
    }

    protected Drawable( final String mustHave, final List<String> asList, final double x, final double y, final int delay ) {
        this( asList, x, y, delay );
        this.addSwitchingImage( mustHave );
    }

    private Drawable( final double x, final double y, final int delay ) {
        this.setX( x );
        this.setY( y );
        this.setSwitchingDelay( delay );
        this.id = id_counter;
        id_counter++;
    }

    protected Drawable( final double x, final double y, final int delay, final String[] pictureFileName ) {
        this( Arrays.asList( pictureFileName ), x, y, delay );
    }

    protected Drawable( final double x, final double y, final double scale, final String picturePath ) {
        this( picturePath, x, y );
        this.scaleImage( scale );
    }

    private Image loadPicture( final String fileName ) {
        if ( fileName == null ) {
            throw new NullPointerException( "Relative path to an image can not be null." );
        }
        else {
            this.name = fileName;
            return TextureBuffer.loadImage( fileName );
        }
    }

    /**
     * Switch switchingImages based on buffer implementation.
     */
    private void switchImages() {
        if ( this.switchingImages.isEmpty() || !this.switchImageAutomatically ) {
            return;
        }
        if ( this.switchingBuffer < this.switchingDelay ) {
            this.switchingBuffer++;
            return;
        }
        switchToNextImage();
    }

    protected void switchToNextImage() {
        this.switchingBuffer = 0;
        final int index = this.switchingImages.indexOf( this.getCurrentImage() );
        if ( index < this.switchingImages.size() - 1 ) {
            this.setCurrentImage( this.switchingImages.get( index + 1 ) );
        }
        else {
            this.setCurrentImage( this.switchingImages.get( 0 ) );
        }
    }

    protected void scaleImageWidth( final double factor ) {
        if ( factor > 0 ) {
            this.width *= factor;
        }
        else {
            this.setX( this.getX() + this.getWidth() );
            this.width *= factor;
        }
        this.scaleX *= factor;
        this.imageView.setFitWidth( scaleX );
        this.imageView.setScaleX( scaleX );
    }

    protected void scaleImageHeight( final double factor ) {
        if ( factor > 0 ) {
            this.height *= factor;
        }
        else {
            this.height *= factor;
        }
        this.scaleY *= factor;
        this.imageView.setFitHeight( scaleY );
        this.imageView.setScaleY( scaleY );
    }

    protected void scaleImage( final double factor ) {
        scaleImageHeight( factor );
        scaleImageWidth( factor );
    }

    // ---------------------------------- START DRAW ----------------------------------

    public synchronized void draw( Canvas canvas ) {
        draw( canvas, 0, 0 );
    }

    public synchronized void draw( Canvas canvas, final double[] offset_pos ) {
        draw( canvas, offset_pos[ 0 ], offset_pos[ 1 ] );
    }

    public synchronized void draw( Canvas canvas, final double offset_to_new_x, final double offset_to_new_y ) {
        draw( canvas, canvas.getWidth(), canvas.getHeight(), offset_to_new_x, offset_to_new_y );
    }

    public synchronized void draw( Canvas canvas,
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
        final double[] in_bounds_pos = calcPosAfterBounds( isInBounds, offset_to_new_x, offset_to_new_y );
        final double[] old_pos       = this.getPos();
        this.setPos( in_bounds_pos );
        this.setPos( beforeDrawing( old_pos, in_bounds_pos ) ); // Maybe reset ? :)
        switchImages();
        //this.setCurrentImage(temp);
        canvas.getGraphicsContext2D().drawImage( this.getCurrentImage(),
                                                 this.x, this.y, this.width, this.height
                                               );
    }

    public synchronized void draw( GraphicsContext gc ) {
        gc.drawImage( this.getCurrentImage(), this.x, this.y, this.width, this.height );
    }

    /**
     * Override this method, to apply any new checks or manipulate the position before the new position is drawn.
     *
     * @param current_pos
     *         The current position of the Drawable
     * @param new_pos
     *         The next position of the Drawable
     *
     * @return Returns the new position of the Drawable.
     */
    protected synchronized double[] beforeDrawing( double[] current_pos, double[] new_pos ) {
        return new_pos;
    }

    // ---------------------------------- END DRAW ----------------------------------

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

    @Override
    public boolean equals( Object obj ) {
        if ( obj instanceof Drawable ) {
            Drawable d = (Drawable) obj;
            return Arrays.equals( this.getPos(), d.getPos() ) &&
                   this.getHeight() == d.getHeight() &&
                   this.getWidth() == d.getWidth() &&
                   this.getName().equals( d.getName() ) &&
                   this.getCurrentImage().equals( d.getCurrentImage() )
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
        StringBuilder result = new StringBuilder( "name:" ).append( this.getName() ).append( ", " );
        result.append( "x:" ).append( this.getX() ).append( ", " );
        result.append( "y:" ).append( this.getY() ).append( ", " );
        result.append( "width:" ).append( this.getWidth() ).append( ", " );
        result.append( "height:" ).append( this.getHeight() );
        return result.toString();
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

    protected double getScaleY() {
        return this.scaleY;
    }

    protected double getScaleX() {
        return this.scaleX;
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

    public void setSize( double size ) {
        this.width = size;
        this.height = size;
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

    public double getSwitchingDelay() {
        return switchingDelay;
    }

    protected void setSwitchingDelay( double switchingDelay ) {
        this.switchingDelay = switchingDelay;
    }

    public boolean isSwitchImageAutomatically() {
        return switchImageAutomatically;
    }

    public void setSwitchImageAutomatically( boolean switchImageAutomatically ) {
        this.switchImageAutomatically = switchImageAutomatically;
    }

    protected Image getCurrentImage() {
        return this.imageView.getImage();
    }

    public void setCurrentImage( Image currentImage ) {
        this.imageView.setImage( currentImage );
    }

    public void setCurrentImage( final String filePath ) {
        this.imageView.setImage( loadPicture( filePath ) );
        this.setHeight( this.getCurrentImage().getHeight() );
        this.setWidth( this.getCurrentImage().getWidth() );
    }

    private ArrayList<Image> getSwitchingImages() {
        return switchingImages;
    }

    public void setSwitchingImages( LinkedList<Image> switchingImages ) {
        this.switchingImages.addAll( switchingImages );
    }

    private void addSwitchingImage( String imagePath ) {
        this.getSwitchingImages().add( this.loadPicture( imagePath ) );
    }

    private void setSwitchingImages( List<String> imagePaths ) {
        for ( String filePath : imagePaths ) {
            this.switchingImages.add( loadPicture( filePath ) );
        }
        if ( this.switchingImages.size() > 0 ) {
            this.setCurrentImage( this.switchingImages.get( 0 ) );
            this.setWidth( this.getCurrentImage().getWidth() );
            this.setHeight( this.getCurrentImage().getHeight() );
        }
    }
}
