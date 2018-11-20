package de.hsh.alexander.game.actor;

import de.hsh.alexander.util.Path;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Actor {

    static final String actorLocation = Path.getExecutionLocation() + "de/hsh/alexander/game/actor/";

    private double x;
    private double y;

    private double height;
    private double width;


    private Image picture;

    Actor( String pictureFileName ) {
        this( pictureFileName, 0, 0 );
    }

    Actor( String pictureFileName, double x, double y ) {
        loadPicture( pictureFileName );
        this.setHeight( this.getPicture().getHeight() );
        this.setWidth( this.getPicture().getWidth() );
        this.setX( x );
        this.setY( y );
    }

    Actor( String pictureFileName, double x, double y, double height, double width ) {
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


    public void setPos( double x, double y ) {
        this.setX( x );
        this.setY( y );
    }

    public void movePos( double vertical, double horizontal ) {
        this.setX( this.getX() + horizontal );
        this.setY( this.getY() + vertical );
    }


    public double getX() {
        return x;
    }

    public double getY() {
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

    public void draw( GraphicsContext gc ) {
        gc.drawImage( this.picture, this.x, this.y, this.width, this.height );
    }

    public Image getPicture() {
        return picture;
    }

    public void setPicture( Image picture ) {
        this.picture = picture;
    }
}
