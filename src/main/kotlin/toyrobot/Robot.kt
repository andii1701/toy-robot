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
        heading = when(turnDirection)  {
            TurnDirection.LEFT -> previoudHeading(heading)
            TurnDirection.RIGHT -> nextHeading(heading)
        }
    }
}