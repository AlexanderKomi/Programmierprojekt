package common.engine.components.menu

abstract class MainMenu : Menu() {

    abstract fun init()

    abstract fun title(): String
}
