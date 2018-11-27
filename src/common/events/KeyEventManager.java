package common.events;

import common.util.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

public class KeyEventManager extends Observable implements KeyListener, EventHandler, Observer {

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

    public void add( java.awt.Canvas canvas ) {
        canvas.addKeyListener( this );
    }

    public void add( Canvas canvas ) {
        canvas.setOnKeyPressed( this::keyPressed );
        canvas.setOnKeyReleased( this::keyReleased );
        canvas.addEventHandler( KeyEvent.KEY_PRESSED, this );
        canvas.addEventHandler( KeyEvent.KEY_RELEASED, this );
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

    @Override
    public void handle( Event event ) {
        setChanged();
        notifyObservers( event );
    }

    @Override
    public void update( Observable o, Object arg ) {
        Logger.log( this.getClass() + ": " + o + ", " + arg );
        this.setChanged();
        this.notifyObservers( arg );
    }
}
