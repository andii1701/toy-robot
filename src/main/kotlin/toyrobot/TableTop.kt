package toyrobot


class TableTopException(override var message:String): Exception(message)

const val defaultXLength = 5
const val defaultYLength = 5

class TableTop( val xLength: Int = defaultXLength, val yLength: Int = defaultYLength) {

    init {
        if (xLength < 0 || yLength < 0) throw TableTopException("Table dimensions must not be negative")
    }

    fun onTable(x: Int, y: Int): Boolean = x in 0 until xLength && y in 0 until yLength
}