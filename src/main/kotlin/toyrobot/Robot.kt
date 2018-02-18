package toyrobot


class UnexpectedCommandException(override var message:String): Exception(message)


class Robot(private var tableTop: TableTop)  {

    private var v: SimpleVector? = null

    private val moveByNumberOfUnits = 1

    fun getX(): Int? = v?.x

    fun getY(): Int? = v?.y

    fun getHeading(): Heading? = v?.heading

    fun placed(): Boolean = v != null

    fun report(): String? = if (this.placed()) "${v?.x},${v?.y} and ${v?.heading}" else null

    fun place(x: Int, y: Int, heading: Heading) {
        if (!tableTop.onTable(x, y)) return
        v = SimpleVector(x, y, heading)
    }

    fun turn(turnDirection: TurnDirection) {
        v?.heading = when(turnDirection)  {
            TurnDirection.LEFT -> previousHeading(v!!.heading)
            TurnDirection.RIGHT -> nextHeading(v!!.heading)
        }
    }

    fun move() {
        if (!this.placed()) return

        var newX = v!!.x
        var newY = v!!.y

        when (v?.heading) {
            Heading.NORTH -> newY += moveByNumberOfUnits
            Heading.EAST ->  newX += moveByNumberOfUnits
            Heading.SOUTH -> newY -= moveByNumberOfUnits
            Heading.WEST -> newX -= moveByNumberOfUnits
        }

        if (tableTop.onTable(newX, newY))  {
            v?.x = newX
            v?.y = newY
        }
    }
}