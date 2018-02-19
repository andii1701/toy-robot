package toyrobot.commandParser

import toyrobot.Heading
import toyrobot.SimpleVector


class CommandsParsingException(override var message:String): Exception(message)
class NoCommandsException(override var message:String): Exception(message)


val placeCmdRgex = Regex("""PLACE \d+,\d+,(NORTH|SOUTH|EAST|WEST)""")
val commandRegex = Regex("""(REPORT|MOVE|LEFT|RIGHT|$placeCmdRgex)""")

fun validateCommands(commands: List<String>) {
    if (commands.isEmpty()) throw NoCommandsException("List of commands is empty")
    commands.forEach {
        if (!isValidCommand(it)) { throw CommandsParsingException("Error could not parse command: '$it'") }
    }
}

fun isValidPlaceCommand(command: String): Boolean = placeCmdRgex.matches(command)

fun isValidCommand(command: String): Boolean = commandRegex.matches(command)

fun parsePlaceCommand(cmd: String): SimpleVector  {
    if (!isValidPlaceCommand(cmd)) throw CommandsParsingException("Error could not parse command: $cmd")
        // TODO convert to regex group
    val s = cmd.split(',')
    return SimpleVector(s[0].removePrefix("PLACE ").toInt(),s[1].toInt(), Heading.valueOf(s[2]))
}