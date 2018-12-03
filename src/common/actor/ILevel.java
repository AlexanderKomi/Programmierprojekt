package common.actor;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.io.FileNotFoundException;

public interface ILevel {
    /**
     * Create all your level elements
     */
    void createLevel() throws FileNotFoundException;

    /**
     * What happens, when a key is pressed?
     */
    void keyboardInput( KeyEvent keyEvent );

    /**
     * Draw what you would like to :)
     */
    void render( Canvas canvas, int fps );

}
