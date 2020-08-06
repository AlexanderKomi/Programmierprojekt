package de.hsh.dennis.model

import common.actor.Direction
import common.updates.UpdateCodes
import common.util.Logger
import de.hsh.dennis.model.KeyLayout.Movement.Custom
import de.hsh.dennis.model.NpcLogic.NPCEnums.Spawn
import de.hsh.dennis.model.NpcLogic.NpcHandler
import de.hsh.dennis.model.NpcLogic.SkinConfig
import de.hsh.dennis.model.NpcLogic.SpawnTimer
import de.hsh.dennis.model.NpcLogic.actors.*
import de.hsh.dennis.model.audio.AudioAnalyser
import de.hsh.dennis.model.audio.AudioConfig.MovingSpeeds
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import java.util.*

class GameModel : Observable() {

    //GAME STATES
    private var gameLost = false
    private var breaking = false
    var difficulty = Difficulty.EASY
    var score: Int = 0

    //Objects
    var npcHandler: NpcHandler? = null
    var canvas: Canvas? = null
        set(value) {
            field = value
            npcHandler = NpcHandler(field)
        }
    private var player: Player = Player()
    private var npcList: MutableList<Npc>? = null

    //animation timing values
    private var animationDelay = SkinConfig.Player.resetDelay //animation delay in seconds
    private var skinResetTimer: Long = 0
    private var reset = false
    var fps = -1

    //Audio Stuff
    private var musicStart = true
    private var audioTimer: SpawnTimer = SpawnTimer()
    private var audioDelay = 8.35

    //ausprobierter Wert, ersetzen durch berechneten Wert (Wie lange muss der Sound warten bis er spielen darf um mit
    // den Enemys synchron zu sein. Abhängikkeit Geschwindigkeit, Abstand SpawnPunkt zur Mitte!)
    // --- ACT ------------------------------------------------------------------------------------
    private var actInitialized = false
    var isActing = false
        private set

    fun reset() {
        //Score & Health
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
        audioDelay = 8.35
        actInitialized = false
        isActing = false
    }

    fun act() {

        fun actInit() {
            if (!actInitialized) return
            if (npcHandler == null) {
                npcHandler = NpcHandler(canvas)
            }
            audioDelay = initDifficulty()
            AudioAnalyser.resetSensitivity()
            //npcHandler.loadNpcs(difficulty);
            printLoading()
            score = 0
            health = 100
            actInitialized = true
            isActing = true
            Logger.log("actInit done ...")
        }

        fun updateHealth(addToHealth: Int) {
            if (health + addToHealth <= 0) {
                health = 0
                gameLost = true
            } else if (health + addToHealth <= 100) {
                health += addToHealth
            }
            health_string.set(health.toString())
        }

        fun updateScore(addToScore: Int) {
            score += if (score + addToScore <= 0) 0
            else addToScore
            score_string.set(score.toString())
        }

        fun startMusic() {
            audioTimer.start()
            if (musicStart && audioTimer.currentTimeStamp >= audioDelay) {
                musicStart = false
                MusicPlayer.play()
            }
        }

        fun draw() {
            fun resetSkin() {
                if (reset &&
                    System.currentTimeMillis() - skinResetTimer.toDouble() / 1000 >= animationDelay) {
                    player.setSkinToDefault()
                    reset = false
                }
            }
            clearCanvas()
            resetSkin()
            NpcHandler.drawNpcs()
            canvas!!.graphicsContext2D.drawImage(player.currentImage, player.x, player.y)
        }

        actInit()
        if (!isActing) return
        startMusic()
        updateHealth(npcHandler!!.healthChange)
        updateScore(npcHandler!!.scoreChange)
        npcHandler!!.spawning()
        npcHandler!!.move()
        npcList = npcHandler!!.npcList
        //collideCheck();
        draw()
        checkEnd()
    }

    fun printLoading() {
        if (canvas != null && canvas!!.graphicsContext2D != null) {
            clearCanvas()
            val loading = "[ L O A D I N G ]"
            with(canvas!!.graphicsContext2D) {
                fill = Color.WHITE
                fillText(loading, canvas!!.width / 2 - loading.length * 3, canvas!!.height / 2)
            }
        }
    }

    fun triggerBreak() {
        breaking = true
        MusicPlayer.pause()
        npcHandler?.breakTimeMark = npcHandler?.time!!.currentTimeStamp
        isActing = false
    }

    fun unTriggerBreak() {
        breaking = false
        MusicPlayer.resume()
        npcHandler!!.unTriggerBreak()
        isActing = true
    }

    private fun initDifficulty(): Double {
        //default fps == 60 -> 60 * pro Sekunde bewegt sich ein Npc um 'speed' pixel.
        fun calcAudioDelay(fps: Int, speed: Double): Double =
                (canvas!!.width / 2.0 - SkinConfig.Player.skin_width / 2.0 - 5.0) / (fps * speed)

        return when (difficulty) {
            Difficulty.EASY -> {
                GameModelUtils.initEasyDifficulty(npcHandler!!)
                calcAudioDelay(fps, MovingSpeeds._easy)
            }
            Difficulty.MEDIUM -> {
                GameModelUtils.initMediumDifficulty(npcHandler!!)
                calcAudioDelay(fps, MovingSpeeds._medium)
            }
            Difficulty.HARD -> {
                GameModelUtils.initHardDifficulty(npcHandler!!)
                calcAudioDelay(fps, MovingSpeeds._hard)
            }
            Difficulty.NIGHTMARE -> {
                GameModelUtils.initNightmareDifficulty(npcHandler!!)
                calcAudioDelay(fps, MovingSpeeds._nightmare)
            }
            Difficulty.CUSTOM -> throw IllegalArgumentException("Custom difficulty is not supported")
        }
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
        skinResetTimer = System.currentTimeMillis()
        reset = true
        collideCheck()
    }

    private fun collideCheck() {
        fun chooseNextTargets(): List<Npc> {
            var rightLeftPackage: Npc? = null
            var rightBot: Npc? = null
            var leftBot: Npc? = null
            var rightLeftHacker: Npc? = null

            for (npc in npcList!!) {
                if (rightLeftPackage == null || rightBot == null || leftBot == null || rightLeftHacker == null) {
                    when (npc) {
                        is Package -> if (rightLeftPackage == null) rightLeftPackage = npc
                        is Hacker -> if (rightLeftHacker == null) rightLeftHacker = npc
                        is Bot -> {
                            if (rightBot == null && npc.spawnType == Spawn.RIGHT) rightBot = npc
                            else if (leftBot == null && npc.spawnType == Spawn.LEFT) leftBot = npc
                        }
                    }
                }
            }

            val tempTargets = mutableListOf<Npc>()
            if (rightLeftPackage != null) tempTargets.add(rightLeftPackage)
            if (rightBot != null) tempTargets.add(rightBot)
            if (leftBot != null) tempTargets.add(leftBot)
            if (rightLeftHacker != null) tempTargets.add(rightLeftHacker)
            return tempTargets
        }

        chooseNextTargets()
                .filter { npc -> npc.collidesWithPlayer(player) }
                .forEach { npc -> npcHandler!!.hitNpc(npc) }
    }

    private fun checkEnd() {
        if (!actInitialized) return

        fun end(updateCode: String) {
            isActing = false
            MusicPlayer.stop()
            clearCanvas()
            setChanged()
            notifyObservers(updateCode)
        }

        if (health == 0) end(UpdateCodes.Dennis.gameLost)
        else if (npcHandler!!.isEndReached() && score > 0) end(UpdateCodes.Dennis.gameWon)
        else if (npcHandler!!.isEndReached() && score <= 0) end(UpdateCodes.Dennis.gameLost)
    }

    private fun clearCanvas() {
        canvas!!.graphicsContext2D.drawImage(SkinConfig.Level.level_Background, 0.0, 0.0)
    }

    companion object {
        private var health = 0

        @JvmField
        var health_string: StringProperty = SimpleStringProperty("100")

        @JvmField
        var score_string: StringProperty = SimpleStringProperty("0")
    }
}
