package common.engine.game;

import common.events.KeyEventManager;
import common.events.MouseEventManager;

import java.util.Observer;

public interface IGame extends Observer {

    void update( KeyEventManager keyEventManager, Object arg );

    void update( MouseEventManager mouseEventManagerManager, Object arg );

    void render();

    void exitToMainGUI();
}
