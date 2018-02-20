package toyrobot

import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.StringSpec
import toyrobot.commandParser.CommandsParsingException
import toyrobot.commandParser.NoCommandsException
import toyrobot.commandParser.isValidCommand
import toyrobot.commandParser.parsePlaceCommand
import toyrobot.commandParser.validateCommands
import toyrobot.Heading as H

class CommandsTests : StringSpec() {
    init {
        val validCommands = listOf("PLACE 0,0,NORTH", "MOVE", "LEFT", "RIGHT", "REPORT")

        "validateCommands(commands) should return true if all passed commands are valid" {
            validateCommands(validCommands) shouldBe Unit
        }

        "validateCommands(commands) should throw CommandsParsingException for any incorrect commands" {
            shouldThrow<CommandsParsingException> {
                validateCommands(
                        listOf("PLACE 0,0,NORTH", "MOVE", "LEFT", "RIGHT", "BADCOMMAND"))
            }
        }

        "validateCommands() should throw NoCommandsException for an empty list of commands" {
            shouldThrow<NoCommandsException> { validateCommands(listOf()) }
        }

        "isValidCommand(cmd) should return true for all valid commands" {
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
                    "PLACE -0,0,EAST",
                    "PLACE 5,5,NORTH ",
                    " PLACE 5,5,NORTH",
                    "").forEach { isValidCommand(it) shouldBe false }
        }

        "parsePlaceCommand() should throw exception for an invalid place command" {
            shouldThrow<CommandsParsingException> { parsePlaceCommand("PLACEWRONG 0,0,NORTH") }
        }

        "parsePlaceCommand() should return a valid coordinate and heading when a valid PLACE command is passed" {
            val v = parsePlaceCommand("PLACE 1,2,NORTH")
            (v == SimpleVector(1, 2, H.NORTH))
        }
    }
}