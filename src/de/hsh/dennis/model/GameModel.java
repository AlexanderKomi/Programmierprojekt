package de.hsh.dennis.model;

import common.actor.Direction;
import common.util.Logger;
import de.hsh.dennis.model.KeyLayout.Movement.Custom;
import de.hsh.dennis.model.actors.Package;
import de.hsh.dennis.model.actors.*;
import de.hsh.dennis.model.audio.AudioPlayer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameModel {

    private Canvas canvas;
    private GraphicsContext gc;

    private Player player = new Player();
    private Spawner spawner;
    private ArrayList<Npc> npcList;

    //private int Score = 0;
    //private int Health = 100;

    //animation timing values
    private double animationDelay = 0.5; //animation delay in seconds
    private long skinResetTimer;
    private boolean reset = false;

    //Audio Stuff
    private boolean musicStart = true;
    private AudioPlayer aa = new AudioPlayer();


    // --- ACT ------------------------------------------------------------------------------------
    private boolean ai = false;


    private void act() {
        if (!ai) {
            actInit();
        }

        spawner.move();
        npcList = spawner.getNpcs();
    }

    private void actInit() {
        if (spawner == null) {
            spawner = new Spawner(canvas);
        }
        if (musicStart) {
            musicStart = false;
            aa.loadFile(this.getClass().getResource("audio/jingle.mp3").getPath());
            aa.play();
        }

        ai = true;
    }

    // --- /ACT -----------------------------------------------------------------------------------

    public void userInput(KeyCode k) {

        if (k == Custom.UP || k == Custom.UP_ALT) {
            player.changeSkin(Direction.Up);
            setResetTimer();
            spawnTest();
            return;
        } else if (k == Custom.LEFT || k == Custom.LEFT_ALT) {
            player.changeSkin(Direction.Left);
            setResetTimer();
            return;
        } else if (k == Custom.DOWN || k == Custom.DOWN_ALT) {
            player.changeSkin(Direction.Down);
            setResetTimer();
            return;
        } else if (k == Custom.RIGHT || k == Custom.RIGHT_ALT) {
            player.changeSkin(Direction.Right);
            setResetTimer();
            return;
        }
        Logger.log("unbidden Key Input \'" + k + "\'");
    }

    private void setResetTimer() {
        skinResetTimer = System.currentTimeMillis();
        reset = true;
    }

    private void restetSkin() {
        if (reset) {
            double elapsedTime = System.currentTimeMillis() - skinResetTimer;
            double elapsedSeconds = elapsedTime / 1000;
            if (elapsedSeconds >= animationDelay) {
                player.setSkinToDefault();
                reset = false;
            }
        }
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
        gc = this.canvas.getGraphicsContext2D();
    }

    public void render() {
        act();
        clearCanvas();
        restetSkin();
        drawNpcs();
        gc.drawImage(player.getSkin_current(), player.getPosX(), player.getPosY());
    }

    private void drawNpcs() {
        for (Npc npc : npcList) {
            gc.drawImage(npc.getImage(), npc.getPosX(), npc.getPosY());
        }
    }

    private void clearCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    //debugging
    void spawnTest() {
        spawner.addNpc(new Package(NPCEnums.Spawn.RIGHT));
        spawner.addNpc(new Bot(NPCEnums.Spawn.LEFT));
        spawner.addNpc(new Hacker(NPCEnums.Spawn.RIGHT));
    }
}
