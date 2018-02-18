package toyrobot

const val defaultUnits = 1


enum class Heading { NORTH, EAST, SOUTH, WEST }


enum class TurnDirection { LEFT, RIGHT }

/* Simple version of a vector, which has a x,y position and a North, South, East and West
    heading. */
data class SimpleVector(var x: Int, var y: Int, var heading: Heading) {

    fun move(units:Int = defaultUnits) {
        when (this.heading) {
            Heading.NORTH -> this.y += units
            Heading.EAST ->  this.x += units
            Heading.SOUTH -> this.y -= units
            Heading.WEST -> this.x -= units
        }
    }

    fun turn(turnDirection: TurnDirection)  {

        fun nextHeading(): Heading = when(this.heading) {
            Heading.NORTH -> Heading.EAST
            Heading.EAST -> Heading.SOUTH
            Heading.SOUTH -> Heading.WEST
            Heading.WEST -> Heading.NORTH
        }

        fun previousHeading(): Heading = when(this.heading) {
            Heading.NORTH -> Heading.WEST
            Heading.EAST -> Heading.NORTH
            Heading.SOUTH -> Heading.EAST
            Heading.WEST -> Heading.SOUTH
        }

        this.heading = when(turnDirection)  {
            TurnDirection.LEFT -> previousHeading()
            TurnDirection.RIGHT -> nextHeading()
        }
    }
}