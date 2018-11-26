package common.actor;

import common.util.Path;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Actor {

    private static final String actorLocation = Path.getExecutionLocation() + "de/hsh/alexander/actor/";

    private double x;
    private double y;

    private double height;
    private double width;


    private Image picture;

    protected Actor(String pictureFileName) {
        this( pictureFileName, 0, 0 );
    }

    protected Actor(String pictureFileName, double x, double y) {
        loadPicture( pictureFileName );
        this.setHeight( this.getPicture().getHeight() );
        this.setWidth( this.getPicture().getWidth() );
        this.setX( x );
        this.setY( y );
    }

    protected Actor(String pictureFileName, double x, double y, double height, double width) {
        loadPicture( pictureFileName );
        this.setHeight( height );
        this.setWidth( width );
        this.setX( x );
        this.setY( y );
    }

    private void loadPicture( String fileName ) {
        try {
            String location = actorLocation + fileName;
            picture = new Image( new FileInputStream( location ) );
        }
        catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }
    }

    public void draw( Canvas canvas, double new_x, double new_y ) {

        double temp_x = new_x;
        double temp_y = new_y;

        if ( this.x + new_x < 0 || this.x + new_x + this.width > canvas.getWidth() ) {
            temp_x -= new_x;
        }
        else {
            //temp_x = 0;
        }
        if ( this.y + new_y < 0 || this.y + new_y + this.height > canvas.getHeight() ) {
            temp_y -= new_y;
        }
        else {
            //temp_y = 0;
        }
        movePos( temp_x, temp_y );
        canvas.getGraphicsContext2D().drawImage( this.picture, this.x, this.y, this.width, this.height );
    }

    // GETTER AND SETTER

    void movePos( double vertical, double horizontal ) {
        this.setPos(
                this.getX() + horizontal,
                this.getY() + vertical
                   );
    }

    private void setPos( double x, double y ) {
        this.setX( x );
        this.setY( y );
    }

    private double getX() {
        return x;
    }

    private double getY() {
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


    private Image getPicture() {
        return picture;
    }

    public void setPicture( Image picture ) {
        this.picture = picture;
    }
}
