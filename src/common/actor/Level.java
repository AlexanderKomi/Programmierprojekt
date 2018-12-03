package common.actor;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * A simple level structure.
 *
 * @author Alex
 */
abstract public class Level {

    protected HashSet<Actor>            npcs          = new HashSet<>();
    protected HashSet<ControlableActor> players       = new HashSet<>();
    protected ArrayList<Actor>          levelElements = new ArrayList<>();

    /**
     * Create all your level elements
     */
    abstract public void createLevel() throws FileNotFoundException;

    /**
     * What happens, when a key is pressed?
     */
    abstract public void keyboardInput( KeyEvent keyEvent );

    /**
     * Draw what you would like to :)
     */
    abstract public void render( Canvas canvas, int fps );
}
