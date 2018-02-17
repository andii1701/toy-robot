package toyrobot


class UnexpectedCommandException(override var message:String): Exception(message)


class Robot(var x: Int? = null, var y: Int? = null, var heading: Heading? = null) {

    // TODO is there a more kotlinish way to do this
    fun placed(): Boolean = x != null && y != null && heading != null

    fun report(): String = if (this.placed()) "$x,$y and $heading" else
        throw UnexpectedCommandException("Error report should not be called before robot is placed.")

    fun place(x: Int, y: Int, heading: Heading): Robot  {
        this.x = x
        this.y = y
        this.heading = heading
        return this
    }

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