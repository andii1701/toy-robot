package toyrobot

import toyrobot.commandParser.parsePlaceCommand
import toyrobot.commandParser.validateCommands

class SimulationController(private var robot: Robot, private val commands: List<String>) {

    private var lastReport: String? = null

    init { validateCommands(commands) }

    fun run() {
        commands.forEach {
            if (it.startsWith("PLACE")) { robot.place(parsePlaceCommand(it)) }

            if (robot.placed()) when (it) {
                "REPORT" -> this.report()
                "LEFT" -> robot.turn(TurnDirection.LEFT)
                "RIGHT" -> robot.turn(TurnDirection.RIGHT)
                "MOVE" -> robot.move()
            }
        }
    }

    private fun report() {
        this.lastReport = robot.report()
        println(this.lastReport)
    }

    fun lastReport(): String? = this.lastReport
}