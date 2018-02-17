package toyrobot


class SimulationController(private var robot: Robot, private val commands: List<String>, private val cmdProcessor: Commands)  {
    private var lastReport: String? = null

    init {
        cmdProcessor.validateCommands(commands)

        commands.forEach {
            if (it.startsWith("PLACE")) {
                // TODO unreadable
                val p = cmdProcessor.parsePlace(it)
                robot.place(p.first, p.second, p.third)
            }

            if (robot.placed()) when (it) {
                "REPORT" -> report()
                "LEFT" -> robot.turn(TurnDirection.LEFT)
                "RIGHT" -> robot.turn(TurnDirection.RIGHT)
            }
        }
    }

    private fun report()  {
        lastReport = robot.report()
        print(lastReport)
    }

    // TODO move to getter
    fun lastReport(): String? = lastReport


}
