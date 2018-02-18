import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.StringSpec

import toyrobot.commandParser.validateCommands
import toyrobot.commandParser.isValidCommand
import toyrobot.commandParser.parsePlace
import toyrobot.commandParser.NoCommandsException
import toyrobot.commandParser.CommandsParsingException
import toyrobot.Heading as H
import toyrobot.TurnDirection as D


class CommandsTests : StringSpec() {
    init {
        val validCommands = listOf("PLACE 0,0,NORTH", "MOVE", "LEFT", "RIGHT", "REPORT")

        "validateCommands(commands) should return true if all passed commands are valid" {
            validateCommands(validCommands) shouldBe Unit
        }

        "validateCommands(commands) should throw CommandsParsingException for incorrect commands" {
            shouldThrow<CommandsParsingException> {
                validateCommands(
                        listOf("PLACE 0,0,NORTH", "MOVE", "LEFT", "RIGHT", "BADCOMMAND"))
            }
        }

        "validateCommands() should throw NoCommandsException for an empty list of commands" {
            shouldThrow<NoCommandsException> { validateCommands(listOf()) }
        }

        "isValidCommand(cmd) should return true for valid commands" {
            validCommands.forEach { isValidCommand(it) shouldBe true }
        }

        "isValidCommand(cmd) should return true for all valid PLACE x,y,F command" {
            listOf("PLACE 5,5,NORTH",
                    "PLACE 5,5,SOUTH",
                    "PLACE 5,5,EAST",
                    "PLACE 5,5,WEST",
                    "PLACE 00050,000,WEST").forEach { isValidCommand(it) shouldBe true }
        }

        "isValidCommand(cmd) should return false for all invalid PLACE x,y,F commands" {
            listOf("PLAE 5,5,NORTH",
                    "PLACE -5,5,SOUTH",
                    "PLACE 5,-5,EAST",
                    "PLACE -5,-5,WEST",
                    "PLACE -5,-5,NORTHWEST",
                    "PLACE 00,-00050,EAST",
                    "PLACE 5,5,NORTH ",
                    " PLACE 5,5,NORTH",
                    "").forEach { isValidCommand(it) shouldBe false }
        }

        "parsePlace(..) should throw excaption for invalid place command"  {
            shouldThrow<CommandsParsingException> { parsePlace("PLACEWRONG 0,0,NORTH") }
        }

        "parsePlace(..) should return a valid coordinate and heading when a valid PLACE command is passes"  {
            val p = parsePlace("PLACE 1,2,NORTH")
            p.first shouldBe 1
            p.second shouldBe 2
            p.third shouldBe H.NORTH
        }
    }
}