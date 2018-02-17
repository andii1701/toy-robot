package toyrobot


class NoCommandsException(override var message:String): Exception(message)


class SimulationController {
    // TODO move all commands methods into a commands class
    fun validateCommands(commands: List<String>):Boolean {
        if (commands.isEmpty()) throw NoCommandsException("List of commands is empty")

        commands.forEach() { if (!isValidCommand(it)) { return false } }

        return true
    }

    fun isValidCommand(command: String): Boolean  {
        if (isValidPlaceCommand(command)) { return true }

        return when (command)  {
            "MOVE", "LEFT", "RIGHT", "REPORT" -> true
            else -> false
        }
    }

    private fun isValidPlaceCommand(command: String):
            Boolean = Regex("""PLACE \d+,\d+,(NORTH|SOUTH|EAST|WEST)""").matches(command)
}