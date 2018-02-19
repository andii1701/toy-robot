package toyrobot

const val defaultUnits = 1


enum class Heading { NORTH, EAST, SOUTH, WEST }


enum class TurnDirection { LEFT, RIGHT }

/* Simple version of a vector, which has a x,y position and a North, South, East and West
    heading. */
data class SimpleVector(var x: Int, var y: Int, var heading: Heading) {

    fun move(units:Int = defaultUnits) = when (this.heading) {
            Heading.NORTH -> this.y += units
            Heading.EAST ->  this.x += units
            Heading.SOUTH -> this.y -= units
            Heading.WEST -> this.x -= units
    }

    fun turn(turnDirection: TurnDirection)  {
        val o = this.heading.ordinal
        val s = Heading.values().size

        this.heading = when(turnDirection)  {
            TurnDirection.RIGHT -> Heading.values()[(o + 1) % s]
            // Need extra if statement here because '-1 % 4 == -1'?!!
            TurnDirection.LEFT -> Heading.values()[(if (o == 0) s - 1 else (o - 1) % s)]
        }
    }
}