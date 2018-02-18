package toyrobot

import toyrobot.commandParser.parsePlace
import toyrobot.commandParser.validateCommands


class SimulationController(private var robot: Robot, private val commands: List<String>)  {
    private var lastReport: String? = null

    init {
        validateCommands(commands)

        commands.forEach {
            if (it.startsWith("PLACE")) {
                // TODO unreadable
                val p = parsePlace(it)
                robot.place(p.first, p.second, p.third)
            }

            if (robot.placed()) when (it) {
                "REPORT" -> report()
                "LEFT" -> robot.turn(TurnDirection.LEFT)
                "RIGHT" -> robot.turn(TurnDirection.RIGHT)
                "MOVE" -> robot.move()
            }
        }
    }

    private fun report()  {
        lastReport = robot.report()
        println(lastReport)
    }

    // TODO move to getter
    fun lastReport(): String? = lastReport


}
