package common.events;

import javafx.scene.input.MouseEvent;

import java.util.Observable;

public class MouseEventManager extends Observable {


    public void mouseReleased( MouseEvent mouseEvent ) {
        this.setChanged();
        this.notifyObservers( mouseEvent );
    }

    public void mouseDragged( MouseEvent mouseEvent ) {
        this.setChanged();
        this.notifyObservers( mouseEvent );
    }

    public void mouseClicked( MouseEvent mouseEvent ) {
        this.setChanged();
        this.notifyObservers( mouseEvent );
    }
}