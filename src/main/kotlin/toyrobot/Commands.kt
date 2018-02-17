package toyrobot

import kotlin.math.E


class CommandsParsingException(override var message:String): Exception(message)


class NoCommandsException(override var message:String): Exception(message)


enum class Heading { NORTH, EAST, SOUTH, WEST }

// TODO is there a way to add this to the enum class
fun nextHeading(h: Heading?): Heading? = when(h) {
    Heading.NORTH -> Heading.EAST
    Heading.EAST -> Heading.SOUTH
    Heading.SOUTH -> Heading.WEST
    Heading.WEST -> Heading.NORTH
    null -> null //TODO FIX
}

fun previoudHeading(h: Heading?): Heading? = when(h) {
    Heading.NORTH -> Heading.WEST
    Heading.EAST -> Heading.NORTH
    Heading.SOUTH -> Heading.EAST
    Heading.WEST -> Heading.SOUTH
    null -> null //TODO FIX
}


enum class TurnDirection { LEFT, RIGHT }

// TODO I think this could be better named eg Command parser
class Commands {

    fun validateCommands(commands: List<String>) {
        if (commands.isEmpty()) throw NoCommandsException("List of commands is empty")

        commands.forEach { if (!isValidCommand(it)) { throw CommandsParsingException("Error could not parse commands: $commands") } }
    }

    fun isValidCommand(command: String): Boolean  {
        if (isValidPlaceCommand(command)) { return true }

        return when (command)  {
            "MOVE", "LEFT", "RIGHT", "REPORT" -> true
            else -> false
        }
    }

    fun parsePlace(cmd: String): Triple<Int, Int, Heading>  {
        if (!isValidPlaceCommand(cmd)) throw CommandsParsingException("Error could not parse command: $cmd")
        // TODO convert to regex group
        val s = cmd.split(',')
        return Triple(s[0].removePrefix("PLACE ").toInt(),s[1].toInt(), Heading.valueOf(s[2]))
    }

    private fun isValidPlaceCommand(command: String):
            Boolean = Regex("""PLACE \d+,\d+,(NORTH|SOUTH|EAST|WEST)""").matches(command)
}