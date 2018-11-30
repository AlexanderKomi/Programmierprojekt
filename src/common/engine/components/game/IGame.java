package common.engine.components.game;

import java.util.Observable;
import java.util.Observer;

public interface IGame extends Observer {

    void render();

    void exitToMainGUI();

    void update(Observable o, Object arg);
}
