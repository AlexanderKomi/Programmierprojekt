package common.actor;

import common.util.Path;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Actor {

    private static final String actorLocation = Path.getExecutionLocation() + "de/hsh/alexander/actor/";

    private double x;
    private double y;

    double height;
    double width;

    private String name;

    private Image            currentImage;
    private ArrayList<Image> images = new ArrayList<>();

    protected Actor( String pictureFileName ) throws FileNotFoundException {
        this( pictureFileName, 0, 0 );
    }

    Actor( String pictureFileName, double x, double y ) throws FileNotFoundException {
        this.currentImage = loadPicture( pictureFileName );
        this.setHeight( this.getCurrentImage().getHeight() );
        this.setWidth( this.getCurrentImage().getWidth() );
        this.setX( x );
        this.setY( y );
    }

    private Image loadPicture( String fileName ) throws FileNotFoundException {
        this.name = fileName;
        String location = actorLocation + fileName;
        return new Image( new FileInputStream( location ) );
    }

    private Image getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage( Image currentImage ) {
        this.currentImage = currentImage;
    }

    protected Actor( ArrayList<String> pictureFilePaths, double x, double y, double height, double width )
            throws FileNotFoundException {
        this( x, y, height, width );
        for ( String filePath : pictureFilePaths ) {
            this.images.add( loadPicture( filePath ) );
        }
    }

    private double[] checkBounds( Canvas canvas, double new_x, double new_y ) {
        double[] temp = new double[] {
                new_x, new_y
        };

        if ( (this.x + new_x) < 0 ||
             (this.x + new_x + this.width) > canvas.getWidth() ) {
            temp[ 0 ] = (-new_x);
        }
        if ( (this.y + new_y < 0) ||
             (this.y + new_y + this.height) > canvas.getHeight() ) {
            temp[ 1 ] = (-new_y);
        }
        return temp;
    }

    public boolean doesCollide( Actor other ) {
        return BoundsChecks.doesCollide( this, other ) || BoundsChecks.doesCollide( other, this );
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

    // GETTER AND SETTER

    private void movePos( double horizontal, double vertical ) {
        this.setPos(
                this.getX() + horizontal,
                this.getY() + vertical
                   );
    }

    private void setPos( double x, double y ) {
        this.setX( x );
        this.setY( y );
    }

    double getX() {
        return x;
    }

    double getY() {
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

    private Actor( double x, double y, double height, double width ) {
        this.setHeight( height );
        this.setWidth( width );
        this.setX( x );
        this.setY( y );
    }

    void draw( Canvas canvas, double new_x, double new_y ) {
        double[] temp = checkBounds( canvas, new_x, new_y );
        movePos( temp[ 0 ], temp[ 1 ] );
        canvas.getGraphicsContext2D().drawImage( this.currentImage, this.x, this.y, this.width, this.height );
    }


}
