package common.actor

enum class Direction {
    Non, Up, Down, Left, Right
}

fun Direction.toArray(movementSpeed: Double): DoubleArray {
    val xyTuple = DoubleArray(2)
    when (this) {
        Direction.Down  -> xyTuple[ 1 ] = movementSpeed
        Direction.Up    -> xyTuple[ 1 ] = -movementSpeed
        Direction.Left  -> xyTuple[ 0 ] = -movementSpeed
        Direction.Right -> xyTuple[ 0 ] = movementSpeed
        Direction.Non   -> {}
    }
    return xyTuple
}
