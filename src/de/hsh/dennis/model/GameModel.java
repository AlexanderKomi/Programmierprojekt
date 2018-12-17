package de.hsh.dennis.model;

import common.actor.Direction;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.dennis.model.KeyLayout.Movement.Custom;
import de.hsh.dennis.model.NpcLogic.Config;
import de.hsh.dennis.model.NpcLogic.NPCEnums;
import de.hsh.dennis.model.NpcLogic.NpcHandler;
import de.hsh.dennis.model.NpcLogic.SpawnTimer;
import de.hsh.dennis.model.NpcLogic.actors.Npc;
import de.hsh.dennis.model.NpcLogic.actors.Player;
import de.hsh.dennis.model.audio.AudioPlayer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Observable;

public class GameModel extends Observable {

    //Score & Health
    private int health_init = 100;
    private int score_init = 0;
    public static int health;
    public static StringProperty health_string = new SimpleStringProperty("100");
    public static int score;
    public static StringProperty score_string = new SimpleStringProperty("0");

    //GAME STATES
    public boolean gameLost = false;



    public Config.Level.Difficulty difficulty = Config.Level.Difficulty.EASY;

    //Objects
    private NpcHandler npcHandler;
    private Canvas canvas;
    private GraphicsContext gc;
    private Player player;
    private List<Npc> npcList;



    //animation timing values
    private double animationDelay = 0.1; //animation delay in seconds
    private long skinResetTimer;
    private boolean reset = false;
    public  int fps = -1;

    //Audio Stuff
    private boolean musicStart = true;
    private AudioPlayer ap;
    SpawnTimer audioTimer;
    private double audioDelay = 8.35;       //ausprobierter Wert, ersetzen durch berechneten Wert (Wie lange muss der Sound warten bis er spielen darf um mit den Enemys synchron zu sein. Abhängikkeit Geschwindigkeit, Abstand SpawnPunkt zur Mitte!)

    // --- ACT ------------------------------------------------------------------------------------
    private boolean ai = false;
    private boolean acting = false;

    public GameModel() {
        try {
            player = new Player();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void reset(){
        //Score & Health
        health_init = 100;
        score_init = 0;
        StringProperty health_string = new SimpleStringProperty("100");
        score_string = new SimpleStringProperty("0");

        //GAME STATES
        gameLost = false;
        difficulty = Config.Level.Difficulty.EASY;

        //Objects
        npcHandler = null;
        try {
            player = new Player();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        clearCanvas();
        npcList.clear();
        //animation timing values
        animationDelay = 0.1; //animation delay in seconds
        skinResetTimer = 0;
        reset = false;
        // don't touch fps!

        //Audio Stuff
        musicStart = true;
        ap  = null;
        audioTimer = null;
        audioDelay = 8.35;

        ai = false;
        acting = false;
    }

    public void act() {
            if (!ai) {
                actInit();
            }
        if(acting){

            audioTimer.start();

            if (musicStart && audioTimer.getCurrentTimeStamp() >= audioDelay) {
                musicStart = false;
                ap.play();
            }



        updateHealth(npcHandler.getHealthChange());
        updateScore(npcHandler.getScoreChange());

            npcHandler.spawning();
            npcHandler.move();


            npcList = npcHandler.getNpcList();
            //collideCheck();

            clearCanvas();
            resetSkin();
            NpcHandler.drawNpcs();
            gc.drawImage(player.getSkin(), player.getPosX(), player.getPosY());

        checkEnd();}
    }

    private void actInit() {
        if (npcHandler == null) {
            npcHandler = new NpcHandler(canvas);

        }
        //npcHandler.loadNpcs(difficulty);
        npcHandler.generateNpcs("sound1.mp3");

        ap = new AudioPlayer();
        ap.loadFile(this.getClass().getResource("audio/sound1.mp3").getPath());
        audioTimer = new SpawnTimer();

        score = 0;
        health = 100;
        ai = true;
        acting = true;
    }


    // --- /ACT -----------------------------------------------------------------------------------


    //TODO: CollisionsAbfrage hier anbinden und für das FalschDrücken Bestrafung einfügen!
    public void userInput(KeyCode k) {

        if (k == Custom.UP || k == Custom.UP_ALT) {
            player.changeSkin(Direction.Up);
            setResetTimer();
            collideCheck();
            return;
        } else if (k == Custom.LEFT || k == Custom.LEFT_ALT) {
            player.changeSkin(Direction.Left);
            setResetTimer();
            collideCheck();
            return;
        } else if (k == Custom.DOWN || k == Custom.DOWN_ALT) {
            player.changeSkin(Direction.Down);
            setResetTimer();
            collideCheck();
            return;
        } else if (k == Custom.RIGHT || k == Custom.RIGHT_ALT) {
            player.changeSkin(Direction.Right);
            setResetTimer();
            collideCheck();
            return;
        }

        //Logger.log("unbidden Key Input \'" + k + "\'");
    }



    private void collideCheck() {
        for (Npc npc : npcList) {
            if (npc != null) {

                //hässlich aber korrekt ...
                //BOT
                if (npc.getNpcType() == NPCEnums.NpcType.BOT
                        && ((npc.getSpawnType() == NPCEnums.Spawn.RIGHT && player.getDirection() == Direction.Right) || (npc.getSpawnType() == NPCEnums.Spawn.LEFT && player.getDirection() == Direction.Left))
                        && player.doesCollide(npc)) {
                    npcHandler.hitNpc(npc);
                }

                //PACKAGE
                else if (npc.getNpcType() == NPCEnums.NpcType.PACKAGE
                        && player.getDirection() == Direction.Down
                        && player.doesCollide(npc)) {
                    npcHandler.hitNpc(npc);
                }

                //HACKER
                else if (npc.getNpcType() == NPCEnums.NpcType.HACKER
                        && player.getDirection() == Direction.Up
                        && player.doesCollide(npc)) {
                    npcHandler.hitNpc(npc);
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
                player.setDirection(Direction.Non);
                reset = false;
            }
        }
    }

    private void resetSkinFast() {

                player.setSkinToDefault();
                player.setDirection(Direction.Non);
                reset = false;

    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
        gc = this.canvas.getGraphicsContext2D();
        npcHandler = new NpcHandler(canvas);
    }

    private void checkEnd() {
        if(ai) {
            if (health == 0) {
                acting = false;
                ap.pause();
                clearCanvas();
                Logger.log("1");
                setChanged();
                notifyObservers(UpdateCodes.Dennis.gameLost);

            } else if (npcHandler.isEndReached() && score > 0) {
                acting = false;
                ap.pause();
                clearCanvas();
                Logger.log("2");
                setChanged();
                notifyObservers(UpdateCodes.Dennis.gameWon);

            } else if(npcHandler.isEndReached() && score <= 0){
                acting = false;
                ap.pause();
                clearCanvas();
                Logger.log("3");
                setChanged();
                notifyObservers(UpdateCodes.Dennis.gameLost);

            }
        }
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

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
        audioDelay = 0;
    }


    public Config.Level.Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Config.Level.Difficulty difficulty) {
        this.difficulty = difficulty;
    }

}
