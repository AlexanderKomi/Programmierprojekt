package common.actor

abstract class LevelElement : Actor {
    constructor(x: Double,
                y: Double,
                vararg pictureFilePaths: String) : super(x,
                                                          y,
                                                          0,
                                                          pictureFilePaths as Array<String>)

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
