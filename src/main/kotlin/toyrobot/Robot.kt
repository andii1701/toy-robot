package toyrobot


class UnexpectedCommandException(override var message:String): Exception(message)


class Robot(private var tableTop: TableTop)  {

    private var v: SimpleVector? = null

    fun getX(): Int? = v?.x

    fun getY(): Int? = v?.y

    fun getHeading(): Heading? = v?.heading

    fun placed(): Boolean = v != null

    fun report(): String? = if (this.placed()) "${v?.x},${v?.y} and ${v?.heading}" else null

    fun place(x: Int, y: Int, heading: Heading) {
        if (!tableTop.isOn(x, y)) return
        v = SimpleVector(x, y, heading)
    }

    fun turn(turnDirection: TurnDirection) {
        // TODO move into SimpleVector
        v?.heading = when(turnDirection)  {
            TurnDirection.LEFT -> previousHeading(v!!.heading)
            TurnDirection.RIGHT -> nextHeading(v!!.heading)
        }
    }

    fun move() {
        if (!this.placed()) return

        var newV = this.v!!.copy()
        newV.move()
        if (tableTop.isOn(newV.x, newV.y)) this.v = newV
    }
}