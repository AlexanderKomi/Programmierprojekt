package de.hsh.dennis.model.NpcLogic;

import common.util.Logger;
import common.util.RandomInt;
import de.hsh.dennis.model.NpcLogic.actors.Bot;
import de.hsh.dennis.model.NpcLogic.actors.Hacker;
import de.hsh.dennis.model.NpcLogic.actors.Npc;
import de.hsh.dennis.model.NpcLogic.actors.Package;
import de.hsh.dennis.model.audio.AudioAnalyser;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NpcHandler {

    private int npcLimit = 100;

    private static Canvas canvas;
    private SpawnTimer time = new SpawnTimer();
    private NpcIO npcIO = new NpcIO();
    private AudioAnalyser aa = new AudioAnalyser();

    private Npc[] spawnArray;
    private int spawnIterator = 0;
    private static final List<Npc> npcList = Collections.synchronizedList(new ArrayList());
    private final List<Npc> npcsToRemove = Collections.synchronizedList(new ArrayList());
    private final List<Npc> npcsToHit = Collections.synchronizedList(new ArrayList());

    private int scoreChange = 0;
    private int healthChange = 0;

    private int pointValue = 10;


    public NpcHandler(Canvas canvas) {
        NpcHandler.canvas = canvas;
    }

    public boolean isEndReached() {
        synchronized (npcList) {
            if (spawnIterator == spawnArray.length && npcList.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void spawning() {
        if (spawnArray != null) {
            //time.start();
            if (spawnIterator < spawnArray.length && time.getCurrentTimeStamp() >= spawnArray[spawnIterator].getSpawnTime()) {
                synchronized (npcList) {
                    spawnNpc(spawnArray[spawnIterator]);
                }
                spawnIterator++;
            }
        } else {
            Logger.log("No enemys loaded!");
        }
    }

    public void spawnNpc(Npc npc) {
        synchronized (npcList) {
            if (npcList.size() <= npcLimit) {
                getNpcList().add(npc);
            }
            Logger.log("Npc: " + npc.getNpcType() + " spawned at " + npc.getSpawnTime() + " seconds.");
        }
    }

    public void loadNpcs(Config.Level.Difficulty dif) {

        Npc[] temp = npcIO.loadLevel(dif);

        Arrays.sort(temp);

        spawnArray = temp;
    }

    public void generateNpcs(String pathToMp3) {


        aa.loadSound(pathToMp3);
        List<Double> tempTimes = aa.getSpawnTimes();
        aa.clearAudioFile();

        List<Npc> temp = new ArrayList<>();

        for (Double d : tempTimes) {

            try {
                NPCEnums.Spawn direction;
                int dirTemp = RandomInt.randInt(1, 2);
                if (dirTemp == 1){direction = NPCEnums.Spawn.RIGHT;}else{direction = NPCEnums.Spawn.LEFT;}

                switch (RandomInt.randInt(1, 3)){
                    case 1:
                        temp.add(new Package(direction, (double) d));
                        break;
                    case 2:
                        temp.add(new Bot(direction, (double) d));
                        break;
                    case 3:
                        temp.add(new Hacker(direction, (double) d));
                        break;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        //converting
        spawnArray = new Npc[temp.size()];
        for (int i = 0; i < spawnArray.length; i++) {
            spawnArray[i] = temp.get(i);
        }

    }

    private void removeNpcs() {
        synchronized (npcList) {

            //removing hided enemys
            for (Npc npc : npcsToHit) {
                switch (npc.getNpcType()) {
                    case PACKAGE:
                        scoreChange += pointValue;
                        healthChange += pointValue;
                        break;
                    case BOT:
                        scoreChange += pointValue;
                        break;
                    case HACKER:
                        scoreChange += pointValue;
                        break;
                    default:
                        Logger.log(this.getClass() + "Switching in removeNpcs : default.");
                }
                npcList.remove(npc);
            }
            npcsToHit.clear();

            //removing missed enemys
            for (Npc npc : npcsToRemove) {
                switch (npc.getNpcType()) {
                    case PACKAGE:
                        scoreChange -= pointValue;
                        break;
                    case BOT:
                        scoreChange -= pointValue;
                        healthChange -= pointValue;
                        break;
                    case HACKER:
                        scoreChange -= pointValue;
                        healthChange -= pointValue;
                        break;
                    default:
                        punish();
                        Logger.log(this.getClass() + "Switching in removeNpcs : default.");

                }
                npcList.remove(npc);
                //punish();
            }


            npcsToRemove.clear();
        }

    }

    private void punish() {
        canvas.getGraphicsContext2D().setFill(Color.RED);
        canvas.getGraphicsContext2D().fillRect(0,0, canvas.getWidth(), canvas.getHeight());
    }


    public void hitNpc(Npc npc) {
        synchronized (npcsToHit) {
            npcsToHit.add(npc);
        }
    }

    public List<Npc> getNpcList() {

        return npcList;
    }

    public int getScoreChange() {
        int temp = scoreChange;
        scoreChange = 0;
        return temp;
    }

    public int getHealthChange() {
        int temp = healthChange;
        healthChange = 0;
        return temp;
    }

    public static void drawNpcs() {
        synchronized (npcList) {
            for (Npc npc : npcList) {
                canvas.getGraphicsContext2D().drawImage(npc.getImage(), npc.getPosX(), npc.getPosY());
            }
        }
    }

    public void move() {
        synchronized (npcList) {
            for (Npc npc : npcList) {
                if (npc.getPosX() >= ((canvas.getWidth() / 2.0) - (npc.getImage().getWidth())) && (npc.getPosX() <= (canvas.getWidth() / 2.0))) {
                    npcsToRemove.add(npc);
                } else {
                    npc.move();
                }
            }
            removeNpcs();
        }
    }

}
