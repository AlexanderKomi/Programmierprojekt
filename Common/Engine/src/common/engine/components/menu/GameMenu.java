package common.engine.components.menu;

import java.util.Observable;
import java.util.Observer;

public abstract class GameMenu extends Menu {

    public GameMenu() {
        super();
    }

    public GameMenu(Observer observer) {
        super(observer);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
