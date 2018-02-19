package toyrobot.commandParser

import toyrobot.Heading
import toyrobot.SimpleVector


class CommandsParsingException(override var message:String): Exception(message)
class NoCommandsException(override var message:String): Exception(message)


fun validateCommands(commands: List<String>) {
    if (commands.isEmpty()) throw NoCommandsException("List of commands is empty")
    commands.forEach {
        if (!isValidCommand(it)) { throw CommandsParsingException("Error could not parse command: '$it'") }
    }
}

fun isValidPlaceCommand(command: String):
        Boolean = Regex("""PLACE \d+,\d+,(NORTH|SOUTH|EAST|WEST)""").matches(command)

fun isValidCommand(command: String): Boolean  {
    if (isValidPlaceCommand(command)) { return true }

    return when (command) {
        "MOVE", "LEFT", "RIGHT", "REPORT" -> true
        else -> false
        }
}

fun parsePlaceCommand(cmd: String): SimpleVector  {
    if (!isValidPlaceCommand(cmd)) throw CommandsParsingException("Error could not parse command: $cmd")
        // TODO convert to regex group
    val s = cmd.split(',')
    return SimpleVector(s[0].removePrefix("PLACE ").toInt(),s[1].toInt(), Heading.valueOf(s[2]))
}