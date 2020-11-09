package common.updates


interface Updater {
    fun update(updatable: Updatable, message: String?): Unit
}
