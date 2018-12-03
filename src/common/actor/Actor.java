package common.actor;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

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
    private HashSet<Actor>   collisionActors = new HashSet<>();

    protected Actor( String pictureFileName ) throws FileNotFoundException {
        this( pictureFileName, 0, 0 );
    }

    public Actor( String pictureFileName, double x, double y ) throws FileNotFoundException {
        this.currentImage = loadPicture( pictureFileName );
        this.setHeight( this.getCurrentImage().getHeight() );
        this.setWidth( this.getCurrentImage().getWidth() );
        this.setX( x );
        this.setY( y );
    }

    protected Actor( List<String> pictureFilePaths, double x, double y, int delay )
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
        draw( canvas, 0, 0 );
    }

    void draw( Canvas canvas, double offset_to_new_x, double offset_to_new_y ) {
        double[] old_pos = this.getPos();
        double[] in_bounds_pos = checkBounds( canvas, offset_to_new_x, offset_to_new_y );

        setPos( in_bounds_pos[ 0 ], in_bounds_pos[ 1 ] );

        if(CollisionCheck.applyCollision( this )){
            setPos( old_pos );
        }
        switchImages();
        canvas.getGraphicsContext2D().drawImage( this.currentImage, this.x, this.y, this.width, this.height );
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
        return CollisionCheck.doesCollide( this, other ) || CollisionCheck.doesCollide( other, this );
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

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public HashSet<Actor> getCollisionActors() {
        return collisionActors;
    }

    public void addCollidingActor(Actor other) {
        this.collisionActors.add( other );
    }

    public void addCollidingActor(Actor... others){
        this.collisionActors.addAll( Arrays.asList( others ) );
    }

    public void addCollidingActor( Collection<Actor> others ) {
        this.collisionActors.addAll( others );
    }
}
