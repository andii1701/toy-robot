package toyrobot


class SimulationController(private var robot: Robot?, private val commands: List<String>, private val cmdProcessor: Commands)  {
    private var lastReport: String? = null

    init {
        // TODO ignore commands until place
        cmdProcessor.validateCommands(commands)

        for (cmd in commands)  {
            if (cmd.startsWith("PLACE")) {
                // XXX dumb fix
                if (robot == null) {
                    val p = cmdProcessor.parsePlace(cmd)
                    // TODO really unreadable
                    robot = Robot(p.first, p.second, p.third)
                }
            }
            when (cmd) {
                "REPORT" -> report()
                "LEFT" -> robot?.turn(TurnDirection.LEFT)
                "RIGHT" -> robot?.turn(TurnDirection.RIGHT)
            }
        }
    }

    private fun report()  {
        lastReport = robot?.report()
        print(lastReport)
    }

    // TODO move to setter
    fun lastReport(): String? = lastReport


}
