package toyrobot

data class Robot(var x: Int, var y: Int, var heading: Heading)  {
    fun report(): String = "$x,$y and $heading"
}