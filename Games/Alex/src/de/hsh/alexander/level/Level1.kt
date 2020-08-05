package de.hsh.alexander.level

import de.hsh.alexander.actor.ResourcePaths.Actor.LevelElements.Backgrounds
import de.hsh.alexander.actor.level_elements.Fan
import javafx.scene.canvas.Canvas

class Level1(gameCanvas: Canvas) : PacManLevel(gameCanvas) {
    override fun createLevel(gameCanvas: Canvas) {
        setBackgroundImage(Backgrounds.microChip,
                           background_width,
                           background_height)
        addPlayers(250, 150,
                   550, 350)
        addLevelElements(gameCanvas)
        addEasterEgg(300, 300)
        createDataCoins(gameCanvas)
    }

    private fun addLevelElements(gameCanvas: Canvas?) {
        addLevelElement(Fan(200.0, 50.0))
        addLevelElement(Fan(200.0, 375.0, 5.0))
        fillPins(gameCanvas!!)
        addSMDs(gameCanvas, 200)
    }
}
