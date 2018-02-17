package toyrobot


class UnexpectedCommandException(override var message:String): Exception(message)


class Robot(private var tableTop: TableTop)  {
    var x: Int? = null
    var y: Int? = null
    var heading: Heading? = null

    private val moveByNumberOfUnits = 1

    // TODO is there a more kotlinish way to do this
    fun placed(): Boolean = x != null && y != null && heading != null

    fun report(): String = if (this.placed()) "$x,$y and $heading" else
        throw UnexpectedCommandException("Error report should not be called before robot is placed.")

    fun place(x: Int, y: Int, heading: Heading) {
        if (!tableTop.onTable(x, y)) return
        this.x = x
        this.y = y
        this.heading = heading
    }

    fun turn(turnDirection: TurnDirection) {
        heading = when(turnDirection)  {
            TurnDirection.LEFT -> previoudHeading(heading)
            TurnDirection.RIGHT -> nextHeading(heading)
        }
    }

    fun move() {
        var newX = x!!
        var newY = y!!

        when (heading) {
            Heading.NORTH -> newY += moveByNumberOfUnits
            Heading.EAST ->  newX += moveByNumberOfUnits
            Heading.SOUTH -> newY -= moveByNumberOfUnits
            Heading.WEST -> newX -= moveByNumberOfUnits
        }

        if (tableTop.onTable(newX, newY))  {
            x = newX
            y = newY
        }
    }
}