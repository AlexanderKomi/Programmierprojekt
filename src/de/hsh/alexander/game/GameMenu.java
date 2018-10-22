package de.hsh.alexander.game;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class GameMenu {

    private BorderPane menuPane;

    GameMenu() {
        this.menuPane = initMenuPane();
    }

    private BorderPane initMenuPane() {
        BorderPane bp       = new BorderPane();
        HBox       titleBox = new HBox( new Text( "Pac Man Coop Game Menu" ) );
        titleBox.setAlignment( Pos.CENTER );

        HBox centerBox = new HBox(
                new ImageView(
                        new Image( "http://sample.com/res/flower.png", 100, 100, false, false )
                )
        );
        centerBox.setAlignment( Pos.CENTER );

        bp.setTop( titleBox );
        bp.setCenter( centerBox );
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
