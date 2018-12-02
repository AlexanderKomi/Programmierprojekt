package common.engine.components.game;

import java.util.Observable;
import java.util.Observer;

public interface IGame extends Observer {

    void render(int fps);

    void exitToMainGUI();

    void update(Observable o, Object arg);
}
