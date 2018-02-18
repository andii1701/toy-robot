package toyrobot

const val defaultUnits = 1

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
}