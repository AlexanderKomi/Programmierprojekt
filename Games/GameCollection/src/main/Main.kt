package main

import main.common.GameContainer

fun main(args: Array<String>) {
    val gameContainer = GameContainer()
    if (!gameContainer.isLaunched) {
        gameContainer.startContainer(args)
    }
}
