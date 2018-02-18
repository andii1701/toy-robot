package toyrobot


enum class Heading { NORTH, EAST, SOUTH, WEST }

// TODO is there a way to add this to the enum class
fun nextHeading(h: Heading): Heading = when(h) {
    Heading.NORTH -> Heading.EAST
    Heading.EAST -> Heading.SOUTH
    Heading.SOUTH -> Heading.WEST
    Heading.WEST -> Heading.NORTH
}

fun previousHeading(h: Heading): Heading = when(h) {
    Heading.NORTH -> Heading.WEST
    Heading.EAST -> Heading.NORTH
    Heading.SOUTH -> Heading.EAST
    Heading.WEST -> Heading.SOUTH
}


enum class TurnDirection { LEFT, RIGHT }