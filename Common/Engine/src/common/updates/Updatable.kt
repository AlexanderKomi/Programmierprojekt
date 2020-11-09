package common.updates

interface Updatable {

    val updaterList: MutableList<Updater>

    fun notifyUpdater() = notifyUpdater("")

    fun notifyUpdater(passToUpdaters: String) {
        val copy = this.updaterList
        copy.forEach {
            updater -> updater.update(this, passToUpdaters)
        }
    }

    fun addUpdater(updater: Updater) {
        if (updater !in this) this.updaterList.add(updater)
    }

    fun removeUpdater(updater: Updater) {
        if (updater in this) this.updaterList.remove(updater)
    }

    fun clear() = this.updaterList.clear()

    operator fun contains(updater: Updater): Boolean = this.updaterList.contains(updater)
}
