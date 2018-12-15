package common.actor;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public interface ILevel {
    /**
     * Create all your level elements
     * @param gameCanvas
     */
    void createLevel( Canvas gameCanvas );

    /**
     * What happens, when a key is pressed?
     */
    void keyboardInput( KeyEvent keyEvent );

    /**
     * Draw what you would like to :)
     */
    void render( Canvas canvas, final int fps );

}
