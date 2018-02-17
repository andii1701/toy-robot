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
                    // XXX really unreadable
                    robot = Robot(p.first, p.second, p.third)
                }
            }
            when (cmd) {
                "REPORT" -> {
                    // MOVE to report method
                    lastReport = robot?.report()
                    print(lastReport)
                }
            }
        }
    }
    // TODO move to setter
    fun lastReport(): String? = lastReport


}
