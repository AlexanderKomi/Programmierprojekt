package de.hsh.daniel;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.events.MouseEventManager;
import common.util.Logger;
import de.hsh.daniel.de.hsh.daniel.controller.RAM_MainMenu_controller;
import de.hsh.daniel.model.GameLogic;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class RAM extends GameEntryPoint {

    private RAMFxmlChanger          changer;
    private GraphicsContext         gc;
    private Pane                    root                = new Pane();
    private Canvas                  canvas;
    private MouseEventManager       mouseEventManager   = new MouseEventManager();
    private GameLogic gl;


    public RAM( Observer o ) {
        super( o, WindowConfig.daniel_title );
        changer = new RAMFxmlChanger(this, "view/ram_Menu.fxml", new RAM_MainMenu_controller());
        gl = new GameLogic();
    }

    @Override   //hier melden sich die Controller
    public void update( Observable o, Object arg ) {
        if(arg instanceof Canvas){
            gl.setGameCanvas((Canvas)arg);
        }else{
        changer.changeFxml(o, (String)arg);
    }}

    @Override
    public void render(int fps) {

    }

}
