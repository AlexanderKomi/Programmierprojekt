package de.hsh.daniel;

import de.hsh.daniel.Board;

import de.hsh.alexander.engine.game.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import static de.hsh.alexander.util.Path.getExecutionLocation;

//TODO: 1)Fix seperate opening pane 2) Implement 2-Player mode 3)load pictures onto cards 3) card animation
public class RAM extends Game {
private RAMMenu ramMenu;

    public RAM( Observer o ) {
        super( o, "!R.A.M" );
        this.setGameContentPane( this.gameWindow(o) );
    }

    @Override
    public void update( Observable o, Object arg ) {

    }

    public Pane gameWindow(Observer observer) {
        Pane root = new Pane();

        ramMenu = new RAMMenu(this);

        try {
            FlowPane loadedPane = FXMLLoader.load(getClass().getResource("ram_Menu.fxml"));
            ramMenu.pane = loadedPane;
            return ramMenu.pane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Canvas canvas = new Canvas();
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font("Arial", FontWeight.BOLD, 24);

        return root;



    }
}
