package toyrobot


class Robot(private var tableTop: TableTop)  {

    private var v: SimpleVector? = null

    fun getVec(): SimpleVector? = this.v?.copy()

    fun placed(): Boolean = v != null

    fun report(): String? = if (this.placed()) "${this.v?.x},${this.v?.y},${this.v?.heading}" else null

    fun place(v: SimpleVector) { if (tableTop.isOn(v.x, v.y)) this.v = v.copy() }

    fun turn(turnDirection: TurnDirection) { this.v?.turn(turnDirection) }

    fun move() {
        if (!this.placed()) return

        val tmp = this.v!!.copy()
        tmp.move()
        if (tableTop.isOn(tmp.x, tmp.y)) this.v = tmp
    }
}