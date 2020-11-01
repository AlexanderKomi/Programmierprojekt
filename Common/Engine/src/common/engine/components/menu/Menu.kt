package common.engine.components.menu

import javafx.scene.Scene
import javafx.scene.layout.Pane
import java.util.*

abstract class Menu : Observable(), Observer {
    var scene: Scene? = null
        private set

    override fun update(o: Observable?, arg: Any?) {

    }

    override fun notifyObservers() {
        setChanged()
        super.notifyObservers()
    }

    override fun notifyObservers(arg: Any) {
        setChanged()
        super.notifyObservers(arg)
    }

    fun setMenuPane(menuPane: Pane?) {
        if (menuPane == null) {
            throw NullPointerException("menuPane is null")
        }
        if (scene != null) {
            scene!!.root = menuPane
        } else {
            scene = Scene(menuPane)
        }
    }
}
