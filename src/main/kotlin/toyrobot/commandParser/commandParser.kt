package toyrobot.commandParser

import toyrobot.Heading
import toyrobot.SimpleVector


class CommandsParsingException(override var message:String): Exception(message)
class NoCommandsException(override var message:String): Exception(message)


val placeCmdRegex = Regex("""PLACE (?<x>\d+),(?<y>\d+),(?<heading>NORTH|SOUTH|EAST|WEST)""")
val commandRegex = Regex("""(REPORT|MOVE|LEFT|RIGHT|$placeCmdRegex)""")

fun validateCommands(commands: List<String>) {
    if (commands.isEmpty()) throw NoCommandsException("List of commands is empty")

    commands.forEach {
        if (!isValidCommand(it)) { throw CommandsParsingException("Error could not parse command: '$it'") }
    }
}

fun isValidCommand(command: String): Boolean = commandRegex.matches(command)

fun parsePlaceCommand(cmd: String): SimpleVector  {
    val l = placeCmdRegex.matchEntire(cmd)?.groupValues ?:
        throw CommandsParsingException("Error could not parse command: $cmd")

    return SimpleVector(l[1].toInt(),l[2].toInt(), Heading.valueOf(l[3]))
}