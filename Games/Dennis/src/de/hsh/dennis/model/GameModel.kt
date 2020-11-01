package de.hsh.dennis.model

import common.actor.Direction
import common.updates.UpdateCodes
import common.logger.Logger
import de.hsh.dennis.model.KeyLayout.Movement.Custom
import de.hsh.dennis.model.NpcLogic.NPCEnums.NpcType
import de.hsh.dennis.model.NpcLogic.NPCEnums.Spawn
import de.hsh.dennis.model.NpcLogic.NpcHandler
import de.hsh.dennis.model.NpcLogic.SkinConfig
import de.hsh.dennis.model.NpcLogic.SkinConfig.Level.Difficulty
import de.hsh.dennis.model.NpcLogic.SpawnTimer
import de.hsh.dennis.model.NpcLogic.actors.Npc
import de.hsh.dennis.model.NpcLogic.actors.Player
import de.hsh.dennis.model.audio.AudioConfig.MovingSpeeds
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import java.util.*

class GameModel : Observable() {
    //Score & Health
    private var health_init = 100
    private var score_init = 0

    //GAME STATES
    private var gameLost = false
    private var breaking = false
    var difficulty = Difficulty.EASY

    //Objects
    private var npcHandler: NpcHandler? = null
    private var canvas: Canvas? = null
    private var gc: GraphicsContext? = null
    private var player: Player
    private var npcList: MutableList<Npc>? = null

    //animation timing values
    private var animationDelay = SkinConfig.Player.resetDelay //animation delay in seconds
    private var skinResetTimer: Long = 0
    private var reset = false
    var fps = -1

    //Audio Stuff
    private var musicStart = true
    private var audioTimer: SpawnTimer? = null
    private val audioDelayFixed = 8.35
    private var audioDelay = audioDelayFixed

    //ausprobierter Wert, ersetzen durch berechneten Wert (Wie lange muss der Sound warten bis er spielen darf um mit
    // den Enemys synchron zu sein. Abhängikkeit Geschwindigkeit, Abstand SpawnPunkt zur Mitte!)
    // --- ACT ------------------------------------------------------------------------------------
    private var ai = false
    var isActing = false
        private set

    fun reset() {
        //Score & Health
        health_init = 100
        score_init = 0
        score_string = SimpleStringProperty("0")

        //GAME STATES
        gameLost = false
        difficulty = Difficulty.EASY

        //Objects
        npcHandler = null
        player = Player()
        clearCanvas()
        npcList!!.clear()
        //animation timing values
        animationDelay = 0.1 //animation delay in seconds
        skinResetTimer = 0
        reset = false
        // don't touch fps!

        //Audio Stuff
        musicStart = true
        audioTimer = null
        audioDelay = audioDelayFixed
        ai = false
        isActing = false
    }

    fun act() {
        if (!ai) {
            actInit()
        }
        if (isActing) {
            audioTimer!!.start()
            if (musicStart && audioTimer!!.currentTimeStamp >= audioDelay) {
                musicStart = false
                AudioPlayer.MusicPlayer.play()
            }
            updateHealth(npcHandler!!.healthChange)
            updateScore(npcHandler!!.scoreChange)
            npcHandler!!.spawning()
            npcHandler!!.move()
            npcList = npcHandler!!.npcList
            //collideCheck();
            clearCanvas()
            resetSkin()
            NpcHandler.drawNpcs()
            gc!!.drawImage(player.currentImage, player.x, player.y)
            checkEnd()
        }
    }

    fun printLoading() {
        if (gc != null) {
            clearCanvas()
            val loading = "[ L O A D I N G ]"
            gc!!.fill = Color.WHITE
            gc!!.fillText(loading, canvas!!.width / 2 - loading.length * 3, canvas!!.height / 2)
        }
    }

    fun triggerBreak() {
        breaking = true
        AudioPlayer.MusicPlayer.pause()
        npcHandler!!.triggerBreak()
        isActing = false
    }

    fun unTriggerBreak() {
        breaking = false
        AudioPlayer.MusicPlayer.resume()
        npcHandler!!.unTriggerBreak()
        isActing = true
    }

    private fun actInit() {
        if (npcHandler == null) {
            npcHandler = NpcHandler(canvas)
        }
        audioTimer = SpawnTimer()
        when (difficulty) {
            Difficulty.EASY      -> {
                GameModelUtils.initEasyDifficulty(npcHandler!!)
                audioDelay = calcAudioDelay(fps, MovingSpeeds._easy)
            }
            Difficulty.MEDIUM    -> {
                GameModelUtils.initMediumDifficulty(npcHandler!!)
                audioDelay = calcAudioDelay(fps, MovingSpeeds._medium)
            }
            Difficulty.HARD      -> {
                GameModelUtils.initHardDifficulty(npcHandler!!)
                audioDelay = calcAudioDelay(fps, MovingSpeeds._hard)
            }
            Difficulty.NIGHTMARE -> {
                GameModelUtils.initNightmareDifficulty(npcHandler!!)
                audioDelay = calcAudioDelay(fps, MovingSpeeds._nightmare)
            }
            Difficulty.CUSTOM    -> {
            }
        }
        npcHandler!!.audioAnalyzer.resetSensitivity()
        //npcHandler.loadNpcs(difficulty);
        printLoading()
        score = 0
        health = 100
        ai = true
        isActing = true
        Logger.log("actInit done ...")
    }

    //default fps == 60 -> 60 * pro Sekunde bewegt sich ein Npc um 'speed' pixel.
    private fun calcAudioDelay(fps: Int, speed: Double): Double {
        val widthToMove = canvas!!.width / 2.0 - SkinConfig.Player.skin_width / 2.0 - 5.0 //600.0d
        return widthToMove / (fps * speed)
    }

    // --- /ACT -----------------------------------------------------------------------------------
    fun userInput(k: KeyCode) {
        if (k == Custom.UP || k == Custom.UP_ALT) {
            changeDirection(Direction.Up)
        } else if (k == Custom.LEFT || k == Custom.LEFT_ALT) {
            changeDirection(Direction.Left)
        } else if (k == Custom.DOWN || k == Custom.DOWN_ALT) {
            changeDirection(Direction.Down)
        } else if (k == Custom.RIGHT || k == Custom.RIGHT_ALT) {
            changeDirection(Direction.Right)
        }
        //Logger.log("unbidden Key Input \'" + k + "\'");
    }

    private fun changeDirection(d: Direction) {
        player.changeSkin(d)
        setResetTimer()
        collideCheck()
    }

    private fun collideCheck() {
        val tempTargets = chooseNextTargets()
        for (npc in tempTargets) {

            //hässlich aber korrekt ...
            //BOT
            if (npc.npcType == NpcType.BOT && (npc.spawnType == Spawn.RIGHT && player.direction === Direction.Right ||
                                               npc.spawnType == Spawn.LEFT && player.direction === Direction.Left)
                && player.doesCollide(npc)) {
                npcHandler!!.hitNpc(npc)
            } else if (npc.npcType == NpcType.PACKAGE && player.direction === Direction.Down && player.doesCollide(npc)) {
                npcHandler!!.hitNpc(npc)
            } else if (npc.npcType == NpcType.HACKER && player.direction === Direction.Up && player.doesCollide(npc)) {
                npcHandler!!.hitNpc(npc)
            }
        }
    }

    private fun chooseNextTargets(): ArrayList<Npc> {
        var rightLeftPackage: Npc? = null
        var rightBot: Npc? = null
        var leftBot: Npc? = null
        var rightLeftHacker: Npc? = null
        for (npc in npcList!!) {
            if (rightLeftPackage == null || rightBot == null || leftBot == null || rightLeftHacker == null) {
                when (npc.npcType) {
                    NpcType.PACKAGE -> if (rightLeftPackage == null) {
                        rightLeftPackage = npc
                    }
                    NpcType.BOT     -> if (rightBot == null && npc.spawnType == Spawn.RIGHT) {
                        rightBot = npc
                    } else if (leftBot == null && npc.spawnType == Spawn.LEFT) {
                        leftBot = npc
                    }
                    NpcType.HACKER  -> if (rightLeftHacker == null) {
                        rightLeftHacker = npc
                    }
                }
            }
        }
        val tempTargets = ArrayList<Npc>()
        if (rightLeftPackage != null) {
            tempTargets.add(rightLeftPackage)
        }
        if (rightBot != null) {
            tempTargets.add(rightBot)
        }
        if (leftBot != null) {
            tempTargets.add(leftBot)
        }
        if (rightLeftHacker != null) {
            tempTargets.add(rightLeftHacker)
        }
        return tempTargets
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
    private fun setResetTimer() {
        skinResetTimer = System.currentTimeMillis()
        reset = true
    }

    private fun resetSkin() {
        if (reset) {
            val elapsedTime = System.currentTimeMillis() - skinResetTimer.toDouble()
            val elapsedSeconds = elapsedTime / 1000
            if (elapsedSeconds >= animationDelay) {
                player.setSkinToDefault()
                reset = false
            }
        }
    }

    fun setCanvas(canvas: Canvas?) {
        this.canvas = canvas
        gc = this.canvas!!.graphicsContext2D
        npcHandler = NpcHandler(canvas)
    }

    private fun checkEnd() {
        if (ai) {
            if (health == 0) {
                isActing = false
                AudioPlayer.MusicPlayer.stop()
                clearCanvas()
                Logger.log("checkEnd case : 1")
                setChanged()
                notifyObservers(UpdateCodes.Dennis.gameLost)
            } else if (npcHandler!!.isEndReached && score > 0) {
                isActing = false
                AudioPlayer.MusicPlayer.stop()
                clearCanvas()
                Logger.log("checkEnd case : 2")
                setChanged()
                notifyObservers(UpdateCodes.Dennis.gameWon)
            } else if (npcHandler!!.isEndReached && score <= 0) {
                isActing = false
                AudioPlayer.MusicPlayer.stop()
                clearCanvas()
                Logger.log("checkEnd case : 3")
                setChanged()
                notifyObservers(UpdateCodes.Dennis.gameLost)
            }
        }
    }

    private fun clearCanvas() {
        /*
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        */
        gc!!.drawImage(SkinConfig.Level.level_Background, 0.0, 0.0)
    }

    private fun updateScore(addToScore: Int) {
        if (score + addToScore <= 0) {
            score = 0
        } else {
            score += addToScore
        }
        score_string.set(score.toString())
    }

    private fun updateHealth(addToHealth: Int) {
        if (health + addToHealth <= 0) {
            health = 0
            gameLost = true
        } else if (health + addToHealth <= 100) {
            health += addToHealth
        }
        health_string.set(health.toString())
    }

    var score: Int = 0

    companion object {
        var health = 0
        @JvmField
        var health_string: StringProperty = SimpleStringProperty("100")
        var score = 0
        @JvmField
        var score_string: StringProperty = SimpleStringProperty("0")
    }

    init {
        player = Player()
    }
}
