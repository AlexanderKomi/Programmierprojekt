package common.actor;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import java.util.HashMap;

abstract public class ControlableActor extends Actor {

    protected ControlableActor( final String pictureFileName,
                                final double x,
                                final double y,
                                final HashMap<String, Direction> keymap ) {
        super( pictureFileName, x, y );
        setKeyMap(keymap);
    }

    protected ControlableActor( final String[] pictureFileName,
                                final double x,
                                final double y,
                                final HashMap<String, Direction> keymap,
                                final int delay ) {
        super( x, y, delay, pictureFileName );
        setKeyMap(keymap);
    }

    /**
    * Checks Key Released and Pressed Events.
    * */
    public void move( final KeyEvent keyEvent ) {

        final String keyName   = keyEvent.getCode().getName();
        final String eventName = keyEvent.getEventType().getName();

        if ( eventName.equals( "KEY_PRESSED" ) ) {
            this.movement.onKeyPressed( keyName );
        } else if ( eventName.equals( "KEY_RELEASED" ) ) {
            this.movement.onKeyReleased( keyName );
        }
    }

    @Override
    public void draw( Canvas canvas ) {
        this.draw( canvas, canvas.getWidth(), canvas.getHeight() );
    }

    @Override
    public void draw( Canvas gc, final double canvas_width, final double canvas_height ) {
        final double[] temp = this.calculateNewPosFromInput();
        super.draw( gc, canvas_width, canvas_height, temp[ 0 ], temp[ 1 ] );
    }

    private double[] calculateNewPosFromInput() {
        final double[] xyTuple  = new double[ 2 ];
        final double   velocity = movement.velocity;
        movement.getDirections().stream()
            .filter(movement::isHoldDown)
            .forEach(direction -> {
                final double[] temp = calculateDirectedSpeed( direction, velocity );
                xyTuple[ 0 ] = temp[ 0 ];
                xyTuple[ 1 ] = temp[ 1 ];
            });
        return xyTuple;
    }

    protected double[] calculateDirectedSpeed( final Direction direction, final double movement_speed ) {
        final double[] xyTuple = new double[ 2 ];
        if ( direction == Direction.Down ) {
            xyTuple[ 1 ] += movement_speed;
        }
        if ( direction == Direction.Up ) {
            xyTuple[ 1 ] += -movement_speed;
        }
        if ( direction == Direction.Left ) {
            xyTuple[ 0 ] += -movement_speed;
        }
        if ( direction == Direction.Right ) {
            xyTuple[ 0 ] += movement_speed;
        }
        return xyTuple;
    }
}
