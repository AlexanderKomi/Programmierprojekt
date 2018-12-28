package common.actor;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

/**
 * Bounds checking for collision.
 *
 * @author Kevin Jeske
 * @author Alexander Komischke
 */
public class CollisionCheck {

    /**
     * Just a Wrapper.
     */
    public static boolean[] isInBounds(Drawable a, Canvas canvas) {
        return isInBounds(a.getX(), a.getY(), a.getWidth(), a.getHeight(), canvas);
    }

    public static boolean[] isInBounds( Drawable a, double canvas_widht, double canvas_height ) {
        return isInBounds( a.getX(), a.getY(), a.getWidth(), a.getHeight(), canvas_widht, canvas_height, 0, 0 );
    }


    /**
     * Calculates if a rectangle with a tuple of coordinates (x, y) and given width and height,
     * is positioned in a rectangle canvas_width and canvas_height.
     * <p>
     * The coordinates (new_x, new_y) are an offset to the (x,y) Tuple, not new coordinates!
     * This implementation is in particularly useful to calculate if a new position or the next position of the rectangle is
     * in the greater rectangle.
     *
     * @return an boolean Array with index 0 for x coordinate and
     * * index 1 for y coordinate. When true is written at the corresponding index, it means, that the corresponding
     * coordinate
     * * is in bounds.
     * @author Alexander Komischke
     * @author Kevin Jeske
     */
    static boolean[] isInBounds(final double x,
                                final double y,
                                final double width,
                                final double height,
                                final double canvas_widht,
                                final double canvas_height,
                                final double new_x,
                                final double new_y) {
        boolean[] temp = new boolean[]{
                false, false
        };

        if (width > 0) {
            if ((x + new_x) >= 0 &&
                    (x + new_x + width) <= canvas_widht) {
                temp[0] = true;
            }
        } else if (width < 0) {
            if ((x + new_x) >= 0 &&
                    (x + new_x) <= canvas_widht) {
                temp[0] = true;
            }
        }
        if (height > 0) {
            if ((y + new_y) >= 0 &&
                    (y + new_y + height) <= canvas_height) {
                temp[1] = true;
            }
        } else {
            if ((y + new_y) >= 0 &&
                    (y + new_y) <= canvas_height) {
                temp[1] = true;
            }
        }
        return temp;
    }


    /**
     * Just a Wrapper.
     */
    static boolean[] isInBounds(final double x, final double y, final double width, final double height, Canvas canvas) {
        return isInBounds(x, y, width, height, canvas.getWidth(), canvas.getHeight(), 0, 0);
    }

    /**
     * @return an boolean Array with index 0 for x coordinate and
     * * index 1 for y coordinate. When true is written at the corresponding index, it means, that the corresponding
     * coordinate
     * * is in bounds.
     * @author Alex
     * @author Kevin
     */
    public static boolean[] isInBounds(Drawable a, Canvas canvas,
                                       final double new_x,
                                       final double new_y) {
        return isInBounds(a.getX(), a.getY(), a.getWidth(), a.getHeight(), canvas.getWidth(), canvas.getHeight(), new_x, new_y);
    }

    /**
     * Returns an boolean Array with index 0 equals x coordinate and
     * index 1 equals y coordinate
     *
     * @author Alex
     * @author Kevin
     */
    public static boolean[] isInBounds(Drawable a,
                                       final double canvas_widht,
                                       final double canvas_height,
                                       final double new_x,
                                       final double new_y) {
        return isInBounds(a.getX(), a.getY(), a.getWidth(), a.getHeight(), canvas_widht, canvas_height, new_x, new_y);
    }

    static boolean doesCollide(Drawable a, Drawable b) {
        return checkUpperLeftCorner(a, b) || checkUpperRightCorner(a, b) || checkLowerRightCorner(a, b)
                || checkLowerLeftCorner(a, b);
    }

    public static boolean doesCollide( Drawable a, MouseEvent b ) {
        return doesCollide( a.getX(), a.getY(), a.getWidth(), a.getHeight(),
                            b.getX(), b.getY(), 0, 0 );
    }

    public static boolean doesCollide( Drawable a, final double mouse_x, final double mouse_y ) {
        return doesCollide( a.getX(), a.getY(), a.getWidth(), a.getHeight(),
                            mouse_x, mouse_y, 0, 0 );
    }

    private static boolean doesCollide( final double a_x, final double a_y, final double a_width, final double a_height,
                                        final double b_x, final double b_y, final double b_width, final double b_height ) {
        return checkUpperLeftCorner(a_x, a_y, a_width, a_height, b_x, b_y) ||
                checkUpperRightCorner(a_x, a_y, a_width, a_height, b_x, b_y, b_width) ||
                checkLowerRightCorner(a_x, a_y, a_width, a_height, b_x, b_y, b_width, b_height) ||
                checkLowerLeftCorner(a_x, a_y, a_width, a_height, b_x, b_y, b_width, b_height)

                ;
    }

    /**
     * Obere Linke Ecke von b schneidet a
     *
     * @param a
     * @param b
     * @return
     */
    private static boolean checkUpperLeftCorner(Drawable a, Drawable b) {
        return checkUpperLeftCorner(a.getX(), a.getY(), a.getWidth(), a.getHeight(), b.getX(), b.getY());
    }

    private static boolean checkUpperLeftCorner(final double a_x, final double a_y, final double a_width, final double a_height,
                                                final double b_x, final double b_y) {
        if (a_x <= b_x && a_x + a_width >= b_x) {
            return a_y <= b_y && a_y + a_height >= b_y;
        }
        return false;
    }

    /**
     * Obere Rechte Ecke von b schneidet a
     *
     * @param a
     * @param b
     * @return
     */
    private static boolean checkUpperRightCorner(Drawable a, Drawable b) {
        return checkUpperRightCorner(a.getX(), a.getY(), a.getWidth(), a.getHeight(), b.getX(), b.getY(), b.getWidth());
    }

    private static boolean checkUpperRightCorner(final double a_x, final double a_y, final double a_width, final double a_height,
                                                 final double b_x, final double b_y, final double b_width) {
        if (a_x <= b_x + b_width && a_x + a_width >= b_x + b_width) {
            return a_y <= b_y && a_y + a_height >= b_y;
        }
        return false;
    }

    /**
     * Untere Rechte Ecke von b schneidet a
     *
     * @param a
     * @param b
     * @return
     */
    private static boolean checkLowerRightCorner(Drawable a, Drawable b) {
        return checkLowerRightCorner(a.getX(),
                a.getY(),
                a.getWidth(),
                a.getHeight(),
                b.getX(),
                b.getY(),
                b.getWidth(),
                b.getHeight());
    }

    private static boolean checkLowerRightCorner(final double a_x,
                                                 final double a_y,
                                                 final double a_width,
                                                 final double a_height,
                                                 final double b_x,
                                                 final double b_y,
                                                 final double b_width,
                                                 final double b_height) {
        if (a_x <= b_x + b_width && a_x + a_width >= b_x + b_width) {
            return a_y <= b_y + b_height && a_y + a_height >= b_y + b_height;
        }
        return false;
    }


    /**
     * Untere Linke Ecke von b schneidet a
     *
     * @param a
     * @param b
     * @return
     */
    private static boolean checkLowerLeftCorner(Drawable a, Drawable b) {
        return checkLowerLeftCorner(a.getX(),
                a.getY(),
                a.getWidth(),
                a.getHeight(),
                b.getX(),
                b.getY(),
                b.getWidth(),
                b.getHeight());
    }

    private static boolean checkLowerLeftCorner(final double a_x,
                                                final double a_y,
                                                final double a_width,
                                                final double a_height,
                                                final double b_x,
                                                final double b_y,
                                                final double b_width,
                                                final double b_height) {
        if (a_x <= b_x && a_x + a_width >= b_x) {
            return a_y <= b_y + b_height && a_y + a_height >= b_y + b_height;
        }
        return false;
    }


}
