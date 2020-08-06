package common.actor

abstract class LevelElement : Actor {

    protected constructor(x: Double,
                y: Double,
                pictureFilePaths: Array<String>,
                delay: Int = 0)
            : super(x = x, y = y, delay = delay, pictureFilePaths = pictureFilePaths)

    protected constructor(x: Double,
                          y: Double,
                          picturePath: String,
                          scale: Double) : super(picturePath, x, y) {
        scaleImage(scale)
    }
}
