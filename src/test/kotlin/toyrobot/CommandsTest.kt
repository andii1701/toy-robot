import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.StringSpec

import toyrobot.Commands
import toyrobot.NoCommandsException
import toyrobot.Heading as H
import toyrobot.TurnDirection as D


class CommandsTests : StringSpec() {
    init {
        val validCommands = listOf("PLACE 0,0,NORTH", "MOVE", "LEFT", "RIGHT", "REPORT")

        "Commands().validateCommands(commands) should return true if all passed commands are valid" {
            Commands().validateCommands(validCommands) shouldBe true
        }
        "Commands().validateCommands(commands) should return false if all passed commands are valid" {
            Commands().validateCommands(
                    listOf("PLACE 0,0,NORTH", "MOVE", "LEFT", "RIGHT", "BADCOMMAND")) shouldBe false
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
                    "PLACE 00050,000,WEST").forEach {
                Commands().isValidCommand(it) shouldBe true }
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
                    "").forEach {
                Commands().isValidCommand(it) shouldBe false }
        }

        "Commands().turn(currentDir, newDir) should return correct new direction based on current direction"  {
            Commands().turn(H.NORTH, D.LEFT) shouldBe H.WEST
            Commands().turn(H.NORTH, D.RIGHT) shouldBe H.EAST
            Commands().turn(H.EAST, D.LEFT) shouldBe H.NORTH
            Commands().turn(H.EAST, D.RIGHT) shouldBe H.SOUTH
            Commands().turn(H.SOUTH, D.LEFT) shouldBe H.EAST
            Commands().turn(H.SOUTH, D.RIGHT) shouldBe H.WEST
            Commands().turn(H.WEST, D.LEFT) shouldBe H.SOUTH
            Commands().turn(H.WEST, D.RIGHT) shouldBe H.NORTH
        }
    }
}