package common.events;

import common.util.Logger;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Observable;

public class KeyEventManager extends Observable implements KeyListener {

    public void keyPressed( KeyEvent keyEvent ) {
        Logger.log("KeyEventManager : Key Pressed : " + keyEvent);
        setChanged();
        notifyObservers( keyEvent );
    }

    public void keyReleased( KeyEvent keyEvent ) {
        Logger.log("KeyEventManager : Key Released : " + keyEvent);
        setChanged();
        notifyObservers( keyEvent );
    }

    public void keyTyped( KeyEvent keyEvent ) {
        Logger.log("KeyEventManager : Key typed : " + keyEvent);
        setChanged();
        notifyObservers( keyEvent );
    }

    public void add( Canvas canvas ) {
        canvas.addKeyListener( this );
    }

    @Override
    public void keyTyped( java.awt.event.KeyEvent e ) {
        setChanged();
        notifyObservers( e );
    }

    @Override
    public void keyPressed( java.awt.event.KeyEvent e ) {
        setChanged();
        notifyObservers( e );
    }

    @Override
    public void keyReleased( java.awt.event.KeyEvent e ) {
        setChanged();
        notifyObservers( e );
    }
}
