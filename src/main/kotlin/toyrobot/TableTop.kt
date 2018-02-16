package toyrobot

class TableTopException(override var message:String): Exception(message)


class TableTop(xLength: Int, yLength: Int) {
    val xLength = xLength
    val yLength = yLength

    init {
        if (xLength < 0 || yLength < 0) throw TableTopException("Table dimensions must not be negative")
    }

    fun onTable(x: Int, y: Int): Boolean = x in 0 until xLength && y in 0 until yLength

}