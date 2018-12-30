package de.hsh.dennis.model;

import common.actor.Direction;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.dennis.model.KeyLayout.Movement.Custom;
import de.hsh.dennis.model.NpcLogic.NPCEnums;
import de.hsh.dennis.model.NpcLogic.NpcHandler;
import de.hsh.dennis.model.NpcLogic.SkinConfig;
import de.hsh.dennis.model.NpcLogic.SpawnTimer;
import de.hsh.dennis.model.NpcLogic.actors.Npc;
import de.hsh.dennis.model.NpcLogic.actors.Player;
import de.hsh.dennis.model.audio.AudioConfig;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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


    public boolean breaking = false;


    public SkinConfig.Level.Difficulty difficulty = SkinConfig.Level.Difficulty.EASY;

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
    public int fps = -1;

    //Audio Stuff
    private boolean musicStart = true;
    SpawnTimer audioTimer;
    private final double audioDelayFixed = 8.35d;
    private double audioDelay = audioDelayFixed;       //ausprobierter Wert, ersetzen durch berechneten Wert (Wie lange muss der Sound warten bis er spielen darf um mit den Enemys synchron zu sein. Abhängikkeit Geschwindigkeit, Abstand SpawnPunkt zur Mitte!)

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

    public void reset() {
        //Score & Health
        health_init = 100;
        score_init = 0;
        StringProperty health_string = new SimpleStringProperty("100");
        score_string = new SimpleStringProperty("0");

        //GAME STATES
        gameLost = false;
        difficulty = SkinConfig.Level.Difficulty.EASY;

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
        audioTimer = null;
        audioDelay = audioDelayFixed;

        ai = false;
        acting = false;
    }

    public void act() {

            if (!ai) {
                actInit();
            }
            if (acting) {

                audioTimer.start();
                if (musicStart && audioTimer.getCurrentTimeStamp() >= audioDelay) {
                    musicStart = false;
                    AudioPlayer.MusicPlayer.play();
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

                checkEnd();
            }

    }

    public void triggerBreak(){
        setBreaking(true);
        AudioPlayer.MusicPlayer.pause();
        npcHandler.triggerBreak();
        acting = false;
    }

    public void unTriggerBreak(){
        setBreaking(false);
        AudioPlayer.MusicPlayer.resume();
        npcHandler.unTriggerBreak();
        acting = true;
    }

    private void actInit() {
        if (npcHandler == null) {
            npcHandler = new NpcHandler(canvas);

        }
        audioTimer = new SpawnTimer();
        final String ressourcePath = "/de/hsh/dennis/resources/audioFiles/";
        final String relativeRessource = "../resources/audioFiles/";
        switch (difficulty) {
            case EASY:
                npcHandler.getAudioAnalyzer().setSensitivity(0.2d);
                npcHandler.setDelaysBetweenSpawns(AudioConfig.DelayBetweenSpawns._easy);
                npcHandler.generateNpcs(ressourcePath + AudioConfig.Mp3Paths.easy, AudioConfig.MovingSpeeds._easy);
                AudioPlayer.MusicPlayer.playFile(this.getClass()
                        .getResource(relativeRessource + AudioConfig.Mp3Paths.easy)
                        .getPath());
                audioDelay = calcAudioDelay(getFps(), AudioConfig.MovingSpeeds._easy);
                npcHandler.getAudioAnalyzer().resetSensitivity();
                break;

            case MEDIUM:
                npcHandler.getAudioAnalyzer().setSensitivity(0.1d);
                npcHandler.setDelaysBetweenSpawns(AudioConfig.DelayBetweenSpawns._medium);
                npcHandler.generateNpcs(ressourcePath + AudioConfig.Mp3Paths.medium, AudioConfig.MovingSpeeds._medium);
                AudioPlayer.MusicPlayer.playFile(this.getClass()
                        .getResource(relativeRessource + AudioConfig.Mp3Paths.medium)
                        .getPath());
                audioDelay = calcAudioDelay(getFps(), AudioConfig.MovingSpeeds._medium);
                npcHandler.getAudioAnalyzer().resetSensitivity();
                break;

            case HARD:
                npcHandler.getAudioAnalyzer().setSensitivity(0.05d);
                npcHandler.setDelaysBetweenSpawns(AudioConfig.DelayBetweenSpawns._hard);
                npcHandler.generateNpcs(ressourcePath + AudioConfig.Mp3Paths.hard, AudioConfig.MovingSpeeds._hard);
                AudioPlayer.MusicPlayer.playFile(this.getClass()
                        .getResource(relativeRessource + AudioConfig.Mp3Paths.hard)
                        .getPath());
                audioDelay = calcAudioDelay(getFps(), AudioConfig.MovingSpeeds._hard);
                npcHandler.getAudioAnalyzer().resetSensitivity();
                break;

            case NIGHTMARE:
                npcHandler.getAudioAnalyzer().setSensitivity(0.0d);
                npcHandler.setDelaysBetweenSpawns(AudioConfig.DelayBetweenSpawns._nightmare);
                npcHandler.generateNpcs(ressourcePath + AudioConfig.Mp3Paths.nightmare, AudioConfig.MovingSpeeds._nightmare);
                AudioPlayer.MusicPlayer.playFile(this.getClass()
                        .getResource(relativeRessource + AudioConfig.Mp3Paths.nightmare)
                        .getPath());
                audioDelay = calcAudioDelay(getFps(), AudioConfig.MovingSpeeds._nightmare);
                npcHandler.getAudioAnalyzer().resetSensitivity();
                break;

            case CUSTOM:

                break;
        }
        //npcHandler.loadNpcs(difficulty);


        score = 0;
        health = 100;
        ai = true;
        acting = true;
        Logger.log("actInit done ...");
    }

    //default fps == 60 -> 60 * pro Sekunde bewegt sich ein Npc um 'speed' pixel.
    private double calcAudioDelay(int fps, double speed) {
        double widthToMove = ((canvas.getWidth() / 2d) - (SkinConfig.Player.skin_standard.getWidth() / 2d) - 5d);         //600.0d
        return (widthToMove / (fps * speed));
    }


    // --- /ACT -----------------------------------------------------------------------------------

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

        ArrayList<Npc> tempTargets = chooseNextTargets();


        for (Npc npc : tempTargets) {

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

    private ArrayList<Npc> chooseNextTargets() {

        Npc rightLeftPackage = null;

        Npc rightBot = null;
        Npc leftBot = null;

        Npc rightLeftHacker = null;

        for (Npc npc : npcList) {
            if (rightLeftPackage == null ||
                    rightBot == null ||
                    leftBot == null ||
                    rightLeftHacker == null) {

                switch (npc.getNpcType()) {
                    case PACKAGE:
                        if (rightLeftPackage == null) {
                            rightLeftPackage = npc;
                        }
                        break;
                    case BOT:
                        if (rightBot == null && npc.getSpawnType() == NPCEnums.Spawn.RIGHT) {
                            rightBot = npc;
                        } else if (leftBot == null && npc.getSpawnType() == NPCEnums.Spawn.LEFT) {
                            leftBot = npc;
                        }
                        break;
                    case HACKER:
                        if (rightLeftHacker == null) {
                            rightLeftHacker = npc;
                        }
                        break;
                }
            }
        }

        ArrayList<Npc> tempTargets = new ArrayList<>();
        if (rightLeftPackage != null) {
            tempTargets.add(rightLeftPackage);
        }
        if (rightBot != null) {
            tempTargets.add(rightBot);
        }
        if (leftBot != null) {
            tempTargets.add(leftBot);
        }
        if (rightLeftHacker != null) {
            tempTargets.add(rightLeftHacker);
        }
        return tempTargets;
    }

    /*
    private ArrayList<Npc> chooseNextTargets2() {

        Npc rightPackage = null;
        Npc leftPackage = null;

        Npc rightBot = null;
        Npc leftBot = null;

        Npc rightHacker = null;
        Npc leftHacker = null;

        for (Npc npc : npcList) {
            if (rightPackage == null ||
                    leftPackage == null ||
                    rightBot == null ||
                    leftBot == null ||
                    rightHacker == null ||
                    leftHacker == null) {

                switch (npc.getNpcType()) {
                    case PACKAGE:
                        if (rightPackage == null && npc.getSpawnType() == NPCEnums.Spawn.RIGHT) {
                            rightPackage = npc;
                        } else if (leftPackage == null && npc.getSpawnType() == NPCEnums.Spawn.LEFT) {
                            leftPackage = npc;
                        }
                        break;
                    case BOT:
                        if (rightBot == null && npc.getSpawnType() == NPCEnums.Spawn.RIGHT) {
                            rightBot = npc;
                        } else if (leftBot == null && npc.getSpawnType() == NPCEnums.Spawn.LEFT) {
                            leftBot = npc;
                        }
                        break;
                    case HACKER:
                        if (rightHacker == null && npc.getSpawnType() == NPCEnums.Spawn.RIGHT) {
                            rightHacker = npc;
                        } else if (leftHacker == null && npc.getSpawnType() == NPCEnums.Spawn.LEFT) {
                            leftHacker = npc;
                        }
                        break;
                }
            }
        }

        ArrayList<Npc> tempTargets = new ArrayList<>();
        if (rightPackage != null) {
            tempTargets.add(rightPackage);
        }
        if (leftPackage != null) {
            tempTargets.add(leftPackage);
        }
        if (rightBot != null) {
            tempTargets.add(rightBot);
        }
        if (leftBot != null) {
            tempTargets.add(leftBot);
        }
        if (rightHacker != null) {
            tempTargets.add(rightHacker);
        }
        if (leftHacker != null) {
            tempTargets.add(leftHacker);
        }

        return tempTargets;
    }
    */

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
        if (ai) {
            if (health == 0) {
                acting = false;
                AudioPlayer.MusicPlayer.pause();
                clearCanvas();
                Logger.log("checkEnd case : 1");
                setChanged();
                notifyObservers(UpdateCodes.Dennis.gameLost);

            } else if (npcHandler.isEndReached() && score > 0) {
                acting = false;
                AudioPlayer.MusicPlayer.pause();
                clearCanvas();
                Logger.log("checkEnd case : 2");
                setChanged();
                notifyObservers(UpdateCodes.Dennis.gameWon);

            } else if (npcHandler.isEndReached() && score <= 0) {
                acting = false;
                AudioPlayer.MusicPlayer.pause();
                clearCanvas();
                Logger.log("checkEnd case : 3");
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
        } else if (health + addToHealth <= 100) {
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
    }


    public SkinConfig.Level.Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(SkinConfig.Level.Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }

    public void setBreaking(boolean breaking) {
        this.breaking = breaking;
    }
}
