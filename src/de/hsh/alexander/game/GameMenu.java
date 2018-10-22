package de.hsh.alexander.game;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class GameMenu {

    private BorderPane menuPane;

    GameMenu() {
        this.menuPane = initMenuPane();
    }

    private BorderPane initMenuPane() {
        BorderPane bp = new BorderPane();
        bp.setTop( new Text( "Pac Man Coop Game Menu" ) );
        return bp;
    }

    //-------------------------- GETTER AND SETTER --------------------------

    public BorderPane getMenuPane() {
        return menuPane;
    }

    public void setMenuPane( BorderPane menuPane ) {
        this.menuPane = menuPane;
    }
}
