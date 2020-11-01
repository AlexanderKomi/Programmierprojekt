package common.actor

/**
 * @author Julian Sender
 */
/**
 * Abstract class as blueprint for all collectables
 */
abstract class Collectable : Actor {
    /**
     * GETTER
     * @return Returns a collector-instance
     */
    var collector: Actor? = null
        private set

    /**
     * Extending Actor and needing to give Actor the picture
     * Then following many overloadings of constructor
     * @param pictureFileName path of desired picture
     */
    protected constructor(pictureFileName: String) : super(pictureFileName) {}
    protected constructor(x: Double,
                          y: Double,
                          delay: Int,
                          scale: Double,
                          vararg pictureFilePaths: String) : super(pictureFilePaths.toList(), x, y, delay.toDouble()) {
        scaleImage(scale)
    }

    constructor(x: Double, y: Double, invisiblePictures: String?) : super(invisiblePictures!!,
                                                                          x,
                                                                          y) {
    }

    /**
     * Notifys ovservers if a collectable was collected
     * @param collector param for new collected to notify observers
     */
    open fun wasCollected(collector: Actor) {
        this.collector = collector
        setChanged()
        this.notifyObservers(collected)
    }

    companion object {
        const val collected = "I am collected, remove me :)"
    }
}
