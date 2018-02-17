package toyrobot


class CommandsParsingException(override var message:String): Exception(message)


class NoCommandsException(override var message:String): Exception(message)


enum class Heading { NORTH, EAST, SOUTH, WEST }


enum class TurnDirection { LEFT, RIGHT }

// TODO I think this could be better named eg Command parser
class Commands {

    private val moveByNumberOfUnits = 1
    private val U = moveByNumberOfUnits


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

    fun turn(currentHeading: Heading, turnDirection: TurnDirection): Heading {
        // I'm doing this the dumb way because Kotlin % works diffent to python TODO FIX
        if (currentHeading == Heading.NORTH && turnDirection == TurnDirection.LEFT)
            return Heading.WEST
        else if (currentHeading == Heading.NORTH && turnDirection == TurnDirection.RIGHT)
            return Heading.EAST
        else if (currentHeading == Heading.EAST && turnDirection == TurnDirection.LEFT)
            return Heading.NORTH
        else if (currentHeading == Heading.EAST && turnDirection == TurnDirection.RIGHT)
            return Heading.SOUTH
        else if (currentHeading == Heading.SOUTH && turnDirection == TurnDirection.LEFT)
            return Heading.EAST
        else if (currentHeading == Heading.SOUTH && turnDirection == TurnDirection.RIGHT)
            return Heading.WEST
        else if (currentHeading == Heading.WEST && turnDirection == TurnDirection.LEFT)
            return Heading.SOUTH
        else if (currentHeading == Heading.WEST && turnDirection == TurnDirection.RIGHT)
            return Heading.NORTH
        else throw Exception("Invalid heading or direction")
    }

    fun move(c: Coordinate, heading: Heading): Coordinate = when (heading) {
            Heading.NORTH -> Coordinate(c.x, c.y + U)
            Heading.EAST -> Coordinate(c.x + U, c.y)
            Heading.SOUTH -> Coordinate(c.x, c.y - U)
            Heading.WEST -> Coordinate(c.x - U, c.y)
    }


    private fun isValidPlaceCommand(command: String):
            Boolean = Regex("""PLACE \d+,\d+,(NORTH|SOUTH|EAST|WEST)""").matches(command)
}