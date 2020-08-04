package de.hsh.dennis.model.NpcLogic

import common.util.Logger.log
import de.hsh.dennis.model.NpcLogic.NPCEnums.NpcType
import de.hsh.dennis.model.NpcLogic.NPCEnums.Spawn
import de.hsh.dennis.model.NpcLogic.SkinConfig.Level.Difficulty
import de.hsh.dennis.model.NpcLogic.actors.*
import de.hsh.dennis.model.audio.AudioAnalyser
import javafx.scene.canvas.Canvas
import java.util.*

class NpcHandler(canvas: Canvas?) {
    private val npcLimit = 100
    private val time = SpawnTimer()
    val audioAnalyzer = AudioAnalyser()
    private var spawnArray: Array<Npc> = arrayOf()
    private var spawnIterator = 0
    private val npcsToRemove: MutableList<Npc> = Collections.synchronizedList<Npc>(
            ArrayList())
    private val npcsToHit: MutableList<Npc> = Collections.synchronizedList<Npc>(
            ArrayList())
    val npcList: MutableList<Npc> = mutableListOf()
    var scoreChange = 0
    var healthChange = 0
    private val pointValue = 1
    private val spawnDelay = 0.0
    private var breakTimeElabsed = 0.0
    private var breakTimeMark = 0.0
    val isEndReached: Boolean
        get() {
            synchronized(npcList) {
                if (spawnIterator == spawnArray.size && npcList.isEmpty()) {
                    return true
                }
            }
            return false
        }

    fun triggerBreak() {
        breakTimeMark = time.currentTimeStamp
    }

    fun unTriggerBreak() {
        breakTimeElabsed += time.currentTimeStamp - breakTimeMark
        breakTimeMark = 0.0
    }

    fun spawning() {
        if (spawnIterator < spawnArray.size && time.currentTimeStamp - breakTimeElabsed >=
            spawnArray[spawnIterator].spawnTime) {
            synchronized(npcList) { spawnNpc(spawnArray[spawnIterator]) }
            spawnIterator++
        }
    }

    private fun spawnNpc(npc: Npc) {
        synchronized(npcList) {
            if (npcList.size <= npcLimit) {
                npcList.add(npc)
            }
            log("Npc: " + npc.npcType + " spawned at " + npc.spawnTime + " seconds.")
        }
    }

    fun generateNpcs(mp3Name: String, speed: Double) {
        audioAnalyzer.loadSound(mp3Name)
        val tempTimes = audioAnalyzer.spawnTimes
        audioAnalyzer.clearAudioFile()
        val temp: MutableList<Npc> = ArrayList()
        for (d in tempTimes) {
            if (d >= spawnDelay) {
                var direction: Spawn
                val dirTemp = RandomInt.randInt(1, 2)
                direction = if (dirTemp == 1) {
                    Spawn.RIGHT
                } else {
                    Spawn.LEFT
                }
                when (RandomInt.randInt(1, 3)) {
                    1 -> if (RandomInt.randInt(1, 4) == 1) {
                        temp.add(PackageHealing(direction, d, speed))
                    } else {
                        temp.add(Package(direction, d, speed))
                    }
                    2 -> temp.add(Bot(direction, d, speed))
                    3 -> temp.add(Hacker(direction, d, speed))
                }
            }
        }
        //converting
        spawnArray = spawnArray.indices.map { index -> temp[index] }.toTypedArray()
    }

    private fun removeNpcs() = synchronized(npcList) {
        //removing hided enemys
        for (npc in npcsToHit) {
            when (npc.npcType) {
                NpcType.PACKAGE -> {
                    scoreChange += pointValue
                    if (npc is PackageHealing) {
                        healthChange += pointValue
                    }
                }
                NpcType.BOT     -> scoreChange += pointValue
                NpcType.HACKER  -> scoreChange += pointValue
            }
            npcList.remove(npc)
        }
        npcsToHit.clear()

        //removing missed enemys
        for (npc in npcsToRemove) {
            when (npc.npcType) {
                NpcType.PACKAGE -> scoreChange -= pointValue
                NpcType.BOT     -> {
                    scoreChange -= pointValue
                    healthChange -= pointValue
                }
                NpcType.HACKER  -> {
                    scoreChange -= pointValue
                    healthChange -= pointValue
                }
            }
            npcList.remove(npc)
            //punish();
        }
        npcsToRemove.clear()
    }

    fun hitNpc(npc: Npc) = synchronized(npcsToHit) { npcsToHit.add(npc) }

    fun move() = synchronized(npcList) {
        for (npc in npcList) {
            if (npc.x >= canvas!!.width / 2.0 - npc.currentImage.width && npc.x <= canvas!!.width / 2.0) {
                npcsToRemove.add(npc)
            } else {
                npc.move()
            }
        }
        removeNpcs()
    }

    fun setDelaysBetweenSpawns(delay: Double) {
        audioAnalyzer.setSpawnDelay(delay)
    } /*
    public void reset() {
        npcLimit = 100;
        time = new SpawnTimer();
        npcIO = new NpcIO();
        aa = new AudioAnalyser();

        spawnArray = null;
        spawnIterator = 0;
        npcList.clear();
        npcsToRemove.clear();
        npcsToHit.clear();

        scoreChange = 0;
        healthChange = 0;

        pointValue = 10;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
    */

    companion object {
        private var canvas: Canvas? = null
        private val npcList: MutableList<Npc> = Collections.synchronizedList(ArrayList())

        fun drawNpcs() {
            synchronized(npcList) {
                for (npc in npcList) {
                    canvas!!.graphicsContext2D.drawImage(npc.currentImage, npc.x, npc.y)
                }
            }
        }
    }

    init {
        Companion.canvas = canvas
    }
}
