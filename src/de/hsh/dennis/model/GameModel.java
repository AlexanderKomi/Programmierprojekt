package de.hsh.dennis.model;

import com.google.gson.Gson;
import common.actor.Direction;
import common.updates.UpdateCodes;
import common.util.Logger;
import common.util.Path;
import de.hsh.dennis.model.KeyLayout.Movement.Custom;
import de.hsh.dennis.model.actors.*;
import de.hsh.dennis.model.audio.AudioPlayer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Observable;

public class GameModel extends Observable {

    //Score & Health
    public static int health;
    public static StringProperty health_string = new SimpleStringProperty("100");
    public static int score;
    public static StringProperty score_string = new SimpleStringProperty("0");

    //GAME STATES
    public boolean gameLost = false;
    public boolean gameFinished = false;

    //Objects
    private NpcHandler npcHandler;
    private Canvas canvas;
    private GraphicsContext gc;
    private Player player;
    private List<Npc> npcList;
    private JsonNpc[] spawnArray;

    //animation timing values
    private double animationDelay = 0.5; //animation delay in seconds
    private long skinResetTimer;
    private boolean reset = false;

    //Audio Stuff
    private boolean musicStart = false;
    private AudioPlayer aa = new AudioPlayer();
    // --- ACT ------------------------------------------------------------------------------------
    private boolean ai = false;


    public GameModel() {
        try {
            player = new Player();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void act() {
        if (!checkEnd()) {
            if (!ai) {
                actInit();
            }

            npcHandler.move();
            updateHealth(npcHandler.getHealthChange());
            updateScore(npcHandler.getScoreChange());
            npcList = npcHandler.getNpcList();
            collideCheck();

            clearCanvas();
            resetSkin();
            NpcHandler.drawNpcs();
            gc.drawImage(player.getSkin(), player.getPosX(), player.getPosY());
        } else {
            clearCanvas();
        }
    }

    private void actInit() {
        if (npcHandler == null) {
            npcHandler = new NpcHandler(canvas);
            loadLevel(Config.Level.Difficulty.EASY);
        }
        if (musicStart) {
            musicStart = false;
            aa.loadFile(this.getClass().getResource("audio/jingle.mp3").getPath());
            aa.play();
        }
        score = 0;
        health = 100;
        ai = true;
    }

    private void loadLevel(Config.Level.Difficulty dif) {
//TODO: Loading Json files correctly
        /*
        Gson gson = new Gson();
        spawnArray = new JsonNpc[] {new JsonNpc(0.10, 1.0, NPCEnums.Spawn.LEFT, NPCEnums.NpcType.BOT), new JsonNpc(0.10, 1.0, NPCEnums.Spawn.LEFT, NPCEnums.NpcType.BOT)};
        try {
            String json = gson.toJson(spawnArray);
            FileWriter writer = new FileWriter("test3.json");
            writer.write(json);
            writer.close();
            Logger.log("gson");
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        try {
            spawnArray = new Gson().fromJson(Path.getExecutionLocation() + "de/hsh/dennis/resources/levelFiles/easy.json", JsonNpc[].class);
            Logger.log("gson");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // --- /ACT -----------------------------------------------------------------------------------

    public void userInput(KeyCode k) {

        if (k == Custom.UP || k == Custom.UP_ALT) {
            player.changeSkin(Direction.Up);
            setResetTimer();
            debugging();
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

    private void collideCheck() {
        Npc temp = getNextNpc();
        if (temp != null) {
            if (temp.getSpawnType() == NPCEnums.Spawn.RIGHT && player.getDirection() == Direction.Right) {
                if (player.doesCollide(temp)) {
                    Logger.log("hit right");
                    npcHandler.hitNpc(temp);
                }
            } else if (temp.getSpawnType() == NPCEnums.Spawn.LEFT && player.getDirection() == Direction.Left) {
                if (player.doesCollide(temp)) {
                    Logger.log("hit left");
                    npcHandler.hitNpc(temp);
                }
            }
        }
    }

    private void setResetTimer() {
        skinResetTimer = System.currentTimeMillis();
        reset = true;
    }

    private void resetSkin() {
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

    private boolean checkEnd() {
        if (gameLost) {
            setChanged();
            notifyObservers(UpdateCodes.Dennis.gameLost);
            return true;
        } else if (gameFinished) {
            setChanged();
            notifyObservers(UpdateCodes.Dennis.gameWon);
            return true;
        }
        return false;
    }

    private void clearCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void updateScore(int addToScore) {
        if (score + addToScore <= 0) {
            score = 0;
        } else {
            score += addToScore;
        }
        score_string.set(Integer.toString(score));

    }

    public void updateHealth(int addToHealth) {
        if (health + addToHealth <= 0) {
            health = 0;
            gameLost = true;
        } else {
            health += addToHealth;
        }
        health_string.set(Integer.toString(health));
    }

    private Npc getNextNpc() {
        if (npcList.size() > 0) {
            return npcList.get(0);
        }
        return null;
    }

    //debugging
    void debugging() {
        try {
            //npcHandler.spawn(new Package(NPCEnums.Spawn.RIGHT));
            npcHandler.spawn(new Bot(NPCEnums.Spawn.RIGHT));
            //npcHandler.spawn(new Hacker(NPCEnums.Spawn.RIGHT));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
