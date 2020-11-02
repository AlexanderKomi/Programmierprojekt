package common.engine.components.menu

abstract class MainMenu : Menu() {

    abstract fun initGameNames()

    abstract fun title(): String
}
