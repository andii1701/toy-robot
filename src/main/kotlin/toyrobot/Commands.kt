package toyrobot

import java.security.cert.TrustAnchor


class NoCommandsException(override var message:String): Exception(message)


enum class Heading { NORTH, EAST, SOUTH, WEST }


enum class TurnDirection { LEFT, RIGHT }


class Commands {

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

    fun turn(currentHeading: Heading, turnDirection: TurnDirection): Heading {
        // I'm doing this the dumb was because Kotlin % works diffent to python TODO FIX
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

    private fun isValidPlaceCommand(command: String):
            Boolean = Regex("""PLACE \d+,\d+,(NORTH|SOUTH|EAST|WEST)""").matches(command)
}