package common.actor;

import java.util.Arrays;
import java.util.List;

public class PositionCheck {

    public static boolean applyCollision( Actor actor, Actor... others ) {
        return applyCollision( actor, Arrays.asList( others ) );
    }

    public static boolean applyCollision( Actor actor, List<Actor> others ) {
        for ( Actor a : others ) {
            if ( applyCollision( actor, a ) ) {
                return true;
            }
        }

        return false;
    }

    public static boolean applyCollision( Actor a, Actor b ) {
        double[] old_pos       = { a.getX(), a.getY() };
        boolean  collisionUsed = false;
        if ( a.doesCollide( a ) ) {
            a.setPos( old_pos[ 0 ], old_pos[ 1 ] );
            collisionUsed = true;
        }
        return collisionUsed;
    }
}
