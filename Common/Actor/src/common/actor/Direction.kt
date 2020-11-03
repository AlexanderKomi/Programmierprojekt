package common.actor

enum class Direction {
    Non, Up, Down, Left, Right;


    fun toArray(movementSpeed: Double): DoubleArray = DoubleArray(2).also { xyTuple ->
                when (this) {
                    Down -> xyTuple[1] = movementSpeed
                    Up -> xyTuple[1] = -movementSpeed
                    Left -> xyTuple[0] = -movementSpeed
                    Right -> xyTuple[0] = movementSpeed
                    Non -> {}
                }
            }

}

