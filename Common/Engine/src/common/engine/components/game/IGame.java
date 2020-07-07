package common.engine.components.game;

import java.util.Observable;
import java.util.Observer;

interface IGame extends Observer {

    void render( final int fps );

    void exitToMainGUI();

    void update(Observable o, Object arg);
}
