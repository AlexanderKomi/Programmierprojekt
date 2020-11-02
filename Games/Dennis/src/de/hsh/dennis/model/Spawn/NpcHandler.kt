package de.hsh.dennis.model.Spawn

import common.util.Logger.log
import de.hsh.dennis.model.Spawn.actors.*
import de.hsh.dennis.model.audio.AudioAnalyser
import javafx.scene.canvas.Canvas
import java.util.*

class NpcHandler(canvas: Canvas?) {
    private val npcLimit = 100
    private var spawnArray: Array<Npc> = arrayOf()
    private var spawnIterator = 0
    private val npcsToRemove = mutableListOf<Npc>()
    private val npcsToHit = mutableListOf<Npc>()
    private val pointValue = 1
    private val spawnDelay = 0.0
    private var breakTimeElabsed = 0.0

    val npcList: MutableList<Npc> = mutableListOf()
    val time = SpawnTimer()
    var scoreChange = 0
    var healthChange = 0
    var breakTimeMark = 0.0

    fun isEndReached(): Boolean = synchronized(npcList) {
        return spawnIterator == spawnArray.size && npcList.isEmpty()
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

    private fun spawnNpc(npc: Npc) = synchronized(npcList) {
        if (npcList.size <= npcLimit) {
            npcList.add(npc)
        }
        log("Npc: " + npc.javaClass.name + " spawned at " + npc.spawnTime + " seconds.")
    }

    fun generateNpcs(mp3Name: String, speed: Double) {
        AudioAnalyser.loadSound(mp3Name)
        val spawnTimes = AudioAnalyser.getSpawnTimes()
        AudioAnalyser.clearAudioFile()
        val temp = mutableListOf<Npc>()
        spawnTimes
                .filter { spawnTime -> spawnTime >= spawnDelay }
                .forEach { spawnTime ->
                    val direction =
                            if (RandomInt.randInt(1, 2) == 1) Spawn.RIGHT
                            else Spawn.LEFT

                    when (RandomInt.randInt(1, 3)) {
                        1 -> if (RandomInt.randInt(1, 4) == 1) {
                            temp.add(PackageHealing(direction, spawnTime, speed))
                        } else temp.add(Package(direction, spawnTime, speed))
                        2 -> temp.add(Bot(direction, spawnTime, speed))
                        3 -> temp.add(Hacker(direction, spawnTime, speed))
                    }
                }
        //converting
        spawnArray = spawnArray.indices.map { index -> temp[index] }.toTypedArray()
    }

    private fun removeNpcs() = synchronized(npcList) {
        //removing hided enemys
        npcsToHit.forEach { npc ->
            when (npc) {
                is Package -> {
                    scoreChange += pointValue
                    if (npc is PackageHealing) {
                        healthChange += pointValue
                    }
                }
                is Bot -> scoreChange += pointValue
                is Hacker -> scoreChange += pointValue
            }
            npcList.remove(npc)
        }
        npcsToHit.clear()

        //removing missed enemys
        npcsToRemove.forEach { npc ->
            when (npc) {
                is Package -> scoreChange -= pointValue
                is Bot -> {
                    scoreChange -= pointValue
                    healthChange -= pointValue
                }
                is Hacker -> {
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

    companion object {
        private var canvas: Canvas? = null
        private val npcList: MutableList<Npc> = Collections.synchronizedList(ArrayList())

        fun drawNpcs() = synchronized(npcList) {
            npcList.forEach { npc ->
                canvas!!.graphicsContext2D.drawImage(npc.currentImage, npc.x, npc.y)
            }
        }
    }

    init {
        Companion.canvas = canvas
    }
}
