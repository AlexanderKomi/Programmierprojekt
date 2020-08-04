package de.hsh.dennis.model.NpcLogic

import common.config.WindowConfig
import javafx.scene.image.Image

object SkinConfig {
    private const val resourceFolderPath = "/de/hsh/dennis/resources/"
    private const val skinFolder = resourceFolderPath + "skins/"

    object Level {
        var level_Background = Image(resourceFolderPath + "background_Level.png")
        var speed = 1.0

        enum class Difficulty {
            EASY, MEDIUM, HARD, NIGHTMARE, CUSTOM
        }
    }

    object Player {
        private const val playerPath = skinFolder + "player/"
        const val skin_standard_path = playerPath + "player_standard.png"
        const val skin_standard_right_path = playerPath + "player_standard_right.png"
        const val skin_standard_left_path = playerPath + "player_standard_left.png"
        const val skin_hit_left_path = playerPath + "player_hit_left.png"
        const val skin_hit_right_path = playerPath + "player_hit_right.png"
        const val skin_up_right_path = playerPath + "player_up_right.png"
        const val skin_up_left_path = playerPath + "player_up_left.png"
        const val skin_down_right_path = playerPath + "player_down_right.png"
        const val skin_down_left_path = playerPath + "player_down_left.png"
        var offsetX = 0.0
        var offsetY = 100.0
        const val skin_height = 200
        const val skin_width = 200
        val posX = WindowConfig.window_width / 2.0 - skin_width / 2.0 + offsetX
        val posY = WindowConfig.window_height / 2.0 - skin_width / 2.0 + offsetY
        var resetDelay = 0.2
    }

    object Bot {
        private const val botFolder = skinFolder + "bot/"
        const val skin_standard_path = botFolder + "bot.png"
        const val skin_width = 50
        const val skin_height = 50
    }

    object Hacker {
        private const val hackerFolder = skinFolder + "hacker/"
        const val skin_standard_path = hackerFolder + "hacker.png"
        const val skin_width = 50
        const val skin_height = 50
    }

    object Package {
        private const val packageFolder = skinFolder + "package/"
        const val skin_standard_path = packageFolder + "package.png"
        const val skin_healing_path = packageFolder + "packageHealing.png"
        const val skin_width = 50
        const val skin_height = 50
    }
}
