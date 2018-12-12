package common.actor;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

abstract public class ControlableActor extends Actor {


    protected ControlableActor( String pictureFileName, HashMap<String, Direction> keymap ) {
        super( pictureFileName );
        this.movement.setKeyMap( keymap );
    }

    protected ControlableActor( String pictureFileName, double x, double y, HashMap<String, Direction> keymap ) {
        super( pictureFileName, x, y );
        this.movement.setKeyMap( keymap );
    }

    protected ControlableActor( String[] pictureFileName, double x, double y, HashMap<String, Direction> keymap, int delay ) {
        super( x, y, delay, pictureFileName );
        this.movement.setKeyMap( keymap );
    }

    protected ControlableActor( List<String> pictureFileName, double x, double y, HashMap<String, Direction> keymap, int delay ) {
        super( pictureFileName, x, y, delay );
        this.movement.setKeyMap( keymap );
    }

    protected ControlableActor( double x, double y, HashMap<String, Direction> keymap, int delay,
                                String mustHave, String... pictureFileNames ) {
        super( mustHave, Arrays.asList( pictureFileNames ), x, y, delay );
        this.movement.setKeyMap( keymap );
    }


    /**
    * Checks Key Released and Pressed Events.
    * */
    public void move( KeyEvent keyEvent ) {
        Movement.move( this, keyEvent );
    }

    public void draw( Canvas canvas ) {
        this.draw( canvas.getGraphicsContext2D(), canvas.getWidth(), canvas.getHeight() );
    }

    public void draw( GraphicsContext gc, double canvas_width, double canvas_height ) {
        double[] temp = this.calculateNewPosFromInput();
        super.draw( gc, canvas_width, canvas_height, temp[ 0 ], temp[ 1 ] );
    }

    private double[] calculateNewPosFromInput() {
        double[] xyTuple  = new double[ 2 ];
        double   velocity = getMovement().getVelocity();

        getMovement().getDirections().forEach( direction -> {
            if ( getMovement().isHoldDown( direction ) ) {
                double[] temp = calculateDirectedSpeed( direction, velocity );
                xyTuple[ 0 ] = temp[ 0 ];
                xyTuple[ 1 ] = temp[ 1 ];
            }
        } );
        return xyTuple;
    }

    protected double[] calculateDirectedSpeed( final Direction direction, final double movement_speed ) {
        final double[] xyTuple = new double[ 2 ];
        if ( direction == Direction.Down ) {
            xyTuple[ 0 ] += 0;
            xyTuple[ 1 ] += movement_speed;
        }
        if ( direction == Direction.Up ) {
            xyTuple[ 0 ] += 0;
            xyTuple[ 1 ] += -movement_speed;
        }
        if ( direction == Direction.Left ) {
            xyTuple[ 0 ] += -movement_speed;
            xyTuple[ 1 ] += 0;
        }
        if ( direction == Direction.Right ) {
            xyTuple[ 0 ] += movement_speed;
            xyTuple[ 1 ] += 0;
        }
        return xyTuple;
    }

    //--------------------- Getter and Setter ---------------------

    public void setKeyMap( HashMap<String, Direction> keyMap ) {
        this.movement.setKeyMap( keyMap );
    }

    public Movement getMovement() {
        return movement;
    }
}
