package common;

import javafx.scene.input.KeyEvent;

import java.util.Observable;

public class KeyEventManager extends Observable {

    public void keyPressed( KeyEvent keyEvent ) {
        //Logger.log("KeyEventManager : Key Pressed : "+ keyEvent);
        setChanged();
        notifyObservers( keyEvent );
    }

    public void keyReleased( KeyEvent keyEvent ) {
        //Logger.log("KeyEventManager : Key Released : "+ keyEvent);
        setChanged();
        notifyObservers( keyEvent );
    }

    public void keyTyped( KeyEvent keyEvent ) {
        //Logger.log("KeyEventManager : Key typed : "+ keyEvent);
        setChanged();
        notifyObservers( keyEvent );
    }
}
