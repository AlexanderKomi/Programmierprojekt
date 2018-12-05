package de.hsh.dennis.model.NpcLogic;

import common.util.Logger;
import de.hsh.dennis.model.NpcLogic.actors.Npc;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NpcHandler {

    private int npcLimit = 100;

    private static Canvas canvas;
    private SpawnTimer time = new SpawnTimer();
    private NpcIO npcIO = new NpcIO();

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
            Logger.log("Npc: " + npc.getNpcType() + " spawned after " + time.getCurrentSec() + " seconds.");
        }
    }

    public void loadNpcs(Config.Level.Difficulty dif) {
        Npc[] temp = npcIO.loadLevel(dif);

        Arrays.sort(temp);

        spawnArray = temp;
    }

    //TODO: implement all game behaviors
    private void removeNpcs() {
        synchronized (npcList) {
            for (Npc npc : npcsToHit) {
                switch (npc.getNpcType()) {
                    case PACKAGE:
                        scoreChange += pointValue;
                        healthChange += pointValue;
                        break;
                    case BOT:
                        healthChange += pointValue;
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
                        break;
                    default:
                        Logger.log(this.getClass() + "Switching in removeNpcs : default.");
                }
                npcList.remove(npc);
            }


            npcsToRemove.clear();
        }

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
