package de.hsh.alexander.game;

import de.hsh.alexander.engine.game.GameMenu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class PacManMenu extends GameMenu {

    protected BorderPane initMenuPane() {
        BorderPane bp = new BorderPane();
        bp.setTop( createCenterBox() );
        bp.setCenter( createTopBox() );
        bp.setBottom( createBottomBox() );
        return bp;
    }

    private Node createCenterBox() {
        HBox titleBox = new HBox( new Text( "Pac Man Coop Game Menu" ) );
        titleBox.setAlignment( Pos.CENTER );
        return titleBox;
    }

    private Node createTopBox() {
        HBox centerBox = new HBox(
                new ImageView(
                        new Image( "http://sample.com/res/flower.png", 100, 100, false, false )
                )
        );
        centerBox.setAlignment( Pos.CENTER );
        return centerBox;
    }

    private Node createBottomBox() {

        Button backButton = new Button( "zurück" );

        HBox backButtonBox = new HBox( backButton );
        backButtonBox.setAlignment( Pos.CENTER );

        Button okButton    = new Button( "OK" );
        HBox   okButtonBox = new HBox( okButton );
        okButtonBox.setAlignment( Pos.CENTER );

        HBox placeholder = new HBox();

        HBox bottomBox = new HBox( backButtonBox, okButtonBox, placeholder );
        bottomBox.setAlignment( Pos.CENTER );
        bottomBox.setSpacing( 10.0 );
        bottomBox.setPadding( new Insets( 10, 10, 10, 10 ) );

        return bottomBox;
    }

}
