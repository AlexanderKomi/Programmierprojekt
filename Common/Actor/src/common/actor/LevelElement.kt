package common.actor

abstract class LevelElement : Actor {

    protected constructor(x: Double,
                y: Double,
                pictureFilePaths: Array<String>,
                delay: Double = 0.0)
            : super(x = x, y = y, delay = delay, pictureFilePaths = pictureFilePaths.toList())

    protected constructor(x: Double,
                          y: Double,
                          picturePath: String,
                          scale: Double) : super(x,
                                                 y,
                                                 scale,
                                                 picturePath) {
        scaleImage(scale)
    }
}
