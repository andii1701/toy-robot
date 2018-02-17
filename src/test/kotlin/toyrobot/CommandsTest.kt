import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.StringSpec

import toyrobot.Commands
import toyrobot.Coordinate
import toyrobot.NoCommandsException
import toyrobot.CommandsParsingException
import toyrobot.Heading as H
import toyrobot.TurnDirection as D


class CommandsTests : StringSpec() {
    init {
        val validCommands = listOf("PLACE 0,0,NORTH", "MOVE", "LEFT", "RIGHT", "REPORT")

        "Commands().validateCommands(commands) should return true if all passed commands are valid" {
            Commands().validateCommands(validCommands) shouldBe Unit
        }

        "Commands().validateCommands(commands) should throw CommandsParsingException for incorrect commands" {
            shouldThrow<CommandsParsingException> { Commands().validateCommands(
                    listOf("PLACE 0,0,NORTH", "MOVE", "LEFT", "RIGHT", "BADCOMMAND")) }
        }

        "Commands().validateCommands() should throw NoCommandsException for an empty list of commands" {
            shouldThrow<NoCommandsException> { Commands().validateCommands(listOf()) }
        }

        "Commands().isValidCommand(cmd) should return true for valid commands" {
            validCommands.forEach { Commands().isValidCommand(it) shouldBe true }
        }

        "Commands().isValidCommand(cmd) should return true for all valid PLACE x,y,F command" {
            listOf("PLACE 5,5,NORTH",
                    "PLACE 5,5,SOUTH",
                    "PLACE 5,5,EAST",
                    "PLACE 5,5,WEST",
                    "PLACE 00050,000,WEST").forEach { Commands().isValidCommand(it) shouldBe true }
        }

        "Commands().isValidCommand(cmd) should return false for all invalid PLACE x,y,F commands" {
            listOf("PLAE 5,5,NORTH",
                    "PLACE -5,5,SOUTH",
                    "PLACE 5,-5,EAST",
                    "PLACE -5,-5,WEST",
                    "PLACE -5,-5,NORTHWEST",
                    "PLACE 00,-00050,EAST",
                    "PLACE 5,5,NORTH ",
                    " PLACE 5,5,NORTH",
                    "").forEach { Commands().isValidCommand(it) shouldBe false }
        }

        "Commands().parsePlace(..) should throw excaption for invalid place command"  {
            shouldThrow<CommandsParsingException> { Commands().parsePlace("PLACEWRONG 0,0,NORTH") }
        }

        "Commands().parsePlace(..) should return a valid coordinate and heading when a valid PLACE command is passes"  {
            val p = Commands().parsePlace("PLACE 1,2,NORTH")
            p.first shouldBe 1
            p.second shouldBe 2
            p.third shouldBe H.NORTH
        }

        "Commands().move(...) should return a Coordinate object containing the coodinate of where the move will end"  {
            var c = Commands().move(Coordinate(0,0), H.NORTH)
            c.x shouldBe 0
            c.y shouldBe 1
            c = Commands().move(Coordinate(0,0), H.EAST)
            c.x shouldBe 1
            c.y shouldBe 0
            c = Commands().move(Coordinate(0,0), H.SOUTH)
            c.x shouldBe 0
            c.y shouldBe -1
            c = Commands().move(Coordinate(0,0), H.WEST)
            c.x shouldBe -1
            c.y shouldBe 0
        }
    }
}