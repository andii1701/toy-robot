package toyrobot

data class Robot(var x: Int, var y: Int, var heading: Heading)  {

    fun report(): String = "$x,$y and $heading"

    fun turn(turnDirection: TurnDirection) {
        // I'm doing this the dumb way because Kotlin % works different to python TODO FIX
        if (heading == Heading.NORTH && turnDirection == TurnDirection.LEFT)
            heading = Heading.WEST
        else if (heading == Heading.NORTH && turnDirection == TurnDirection.RIGHT)
            heading = Heading.EAST
        else if (heading == Heading.EAST && turnDirection == TurnDirection.LEFT)
            heading = Heading.NORTH
        else if (heading == Heading.EAST && turnDirection == TurnDirection.RIGHT)
            heading = Heading.SOUTH
        else if (heading == Heading.SOUTH && turnDirection == TurnDirection.LEFT)
            heading = Heading.EAST
        else if (heading == Heading.SOUTH && turnDirection == TurnDirection.RIGHT)
            heading = Heading.WEST
        else if (heading == Heading.WEST && turnDirection == TurnDirection.LEFT)
            heading = Heading.SOUTH
        else if (heading == Heading.WEST && turnDirection == TurnDirection.RIGHT)
            heading = Heading.NORTH
        else throw Exception("Invalid heading or direction")
    }
}