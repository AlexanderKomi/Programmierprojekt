package common.actor;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Actor {

    private double x;
    private double y;

    double height;
    double width;

    private String name;

    private int              switchingBuffer = 0;
    private int              switchingDelay  = 0;
    private Image            currentImage;
    private ArrayList<Image> images          = new ArrayList<>();

    protected Actor( String pictureFileName ) throws FileNotFoundException {
        this( pictureFileName, 0, 0 );
    }

    protected Actor( String pictureFileName, double x, double y ) throws FileNotFoundException {
        this.currentImage = loadPicture( pictureFileName );
        this.setHeight( this.getCurrentImage().getHeight() );
        this.setWidth( this.getCurrentImage().getWidth() );
        this.setX( x );
        this.setY( y );
    }

    Actor( List<String> pictureFilePaths, double x, double y, int delay )
            throws FileNotFoundException {
        this( x, y );
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

    private Actor( double x, double y ) {
        this.x = x;
        this.y = y;
    }

    private Image loadPicture( String fileName ) throws FileNotFoundException {
        this.name = fileName;
        return new Image( new FileInputStream( fileName ) );
    }

    public void draw( Canvas canvas ){
        draw( canvas, this.x, this.y );
    }

    void draw( Canvas canvas, double new_x, double new_y ) {
        setPos( canvas, new_x, new_y );
        switchImages();
        canvas.getGraphicsContext2D().drawImage( this.currentImage, this.x, this.y, this.width, this.height );
    }

    public void drawAndApplyCollision( Canvas canvas, double new_x, double new_y, Actor other ) {
        double[] backup = this.getPos();
        this.draw( canvas, new_x, new_y);
        if ( this.doesCollide( other ) ) {
            this.setPos( backup );
        }
    }

    /**
     * Switch images based on buffer implementation.
     * */
    private void switchImages() {
        if ( this.images.isEmpty() ) {
            return;
        }
        if ( this.switchingBuffer < this.switchingDelay ) {
            this.switchingBuffer++;
            return;
        }
        else {
            this.switchingBuffer = 0;
        }

        int index = this.images.indexOf( this.currentImage );
        if ( index < this.images.size() - 1 ) {
            this.currentImage = this.images.get( index + 1 );
        }
        else {
            this.currentImage = this.images.get( 0 );
        }
    }

    public boolean doesCollide( Actor other ) {
        return BoundsChecks.doesCollide( this, other ) || BoundsChecks.doesCollide( other, this );
    }

    private double[] checkBounds( Canvas canvas, double new_x, double new_y ) {
        double[] temp = new double[] {
                this.x, this.y
        };

        if ( (this.x + new_x) >= 0 &&
             (this.x + new_x + this.width) <= canvas.getWidth() ) {
            temp[ 0 ] += (new_x);
        }
        if ( (this.y + new_y >= 0) &&
             (this.y + new_y + this.height) <= canvas.getHeight() ) {
            temp[ 1 ] += (new_y);
        }
        return temp;
    }

    @Override
    public String toString() {
        String result = "Actor(";
        result += "name:" + this.name + ", ";
        result += "x:" + this.getX() + ", ";
        result += "y:" + this.getY() + ", ";
        result += "width:" + this.width + ", ";
        result += "height:" + this.height;
        return result + ")";
    }

    // ----------------------------------- GETTER AND SETTER -----------------------------------

    private Image getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage( Image currentImage ) {
        this.currentImage = currentImage;
    }

    private void movePos( double horizontal, double vertical ) {
        this.setPos(
                this.getX() + horizontal,
                this.getY() + vertical
                   );
    }

    public void setPos( Canvas canvas, double new_x, double new_y ) {
        double[] temp = checkBounds( canvas, new_x, new_y );
        setPos( temp[ 0 ], temp[ 1 ] );
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

    protected double getX() {
        return x;
    }

    protected double getY() {
        return y;
    }

    void setY( double y ) {
        this.y = y;
    }

    void setX( double x ) {
        this.x = x;
    }

    public void setHeight( double height ) {
        this.height = height;
    }

    public void setWidth( double width ) {
        this.width = width;
    }

}
