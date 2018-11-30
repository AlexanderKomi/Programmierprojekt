package de.hsh.daniel;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.events.MouseEventManager;
import de.hsh.daniel.de.hsh.daniel.controller.RAM_MainMenu_controller;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class RAM extends GameEntryPoint {

    private RAMFxmlChanger       changer;
    private GraphicsContext      gc;
    private Pane                 root = new Pane();
    private Canvas               canvas = new Canvas(1280, 800);
    private MouseEventManager    mem = new MouseEventManager();

    public RAM( Observer o ) {
        super( o, WindowConfig.daniel_title );
        changer = new RAMFxmlChanger(this, RAM_MainMenu_controller.fxml, new RAM_MainMenu_controller());
    }

    @Override
    public void update( Observable o, Object arg ) {
    }

    @Override
    public void render() {

    }

    public Pane pane(Observer o) {
        root.getChildren().add(canvas);
        initializeGraphicsContext();

        return root;
    }


    private void initializeGraphicsContext() {
        javafx.scene.text.Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc = canvas.getGraphicsContext2D();
        gc.setFont( theFont );
        gc.setFill( javafx.scene.paint.Color.GREEN );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth( 1 );
    }

    }
