package toyrobot

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec


class SimulationControllerIntegrationTests : StringSpec() {
    init {
        val tableTop = TableTop(5,5)
        "Verify place and report command work" {
            val sim = SimulationController(Robot(tableTop), listOf("PLACE 0,0,NORTH", "REPORT"))
            sim.run()
            sim.lastReport() shouldBe "0,0,NORTH"
        }

        "Verify place, turn left and report command work" {
            val sim = SimulationController(Robot(tableTop), listOf("PLACE 0,0,NORTH", "LEFT", "REPORT"))
            sim.run()
            sim.lastReport() shouldBe "0,0,WEST"
        }

        "Verify place, turn right and report command work" {
            val sim = SimulationController(Robot(tableTop), listOf("PLACE 0,0,NORTH", "RIGHT", "REPORT"))
            sim.run()
            sim.lastReport() shouldBe "0,0,EAST"
        }

        "Verify multiple place commands work"  {
            val sim = SimulationController(Robot(tableTop), listOf("PLACE 0,0,NORTH", "PLACE 1,2,EAST", "REPORT"))
            sim.run()
            sim.lastReport() shouldBe "1,2,EAST"
        }

        "Verify place, move and report works, this is example A as specified in PROBLEM.md"  {
            val sim = SimulationController(Robot(tableTop), listOf("PLACE 0,0,NORTH", "MOVE", "REPORT"))
            sim.run()
            sim.lastReport() shouldBe "0,1,NORTH"
        }

        "Verify place command is ignored if place is off the table"  {
            val sim = SimulationController(Robot(tableTop), listOf("PLACE 5,0,NORTH", "REPORT"))
            sim.run()
            sim.lastReport() shouldBe null
        }

        "Verify move commands are ignore if robot is moved off the table"  {
            val sim = SimulationController(Robot(tableTop), listOf("PLACE 0,0,SOUTH", "MOVE", "REPORT"))
            sim.run()
            sim.lastReport() shouldBe "0,0,SOUTH"
        }

        "Verify commands before the first valid place command are ignored"  {
            val sim = SimulationController(Robot(tableTop), listOf(
                    "PLACE 5,0,WEST", "LEFT", "MOVE", "REPORT", "PLACE 0,0,NORTH", "REPORT"))
            sim.run()
            sim.lastReport() shouldBe "0,0,NORTH"
        }

        "Verify example B in PROBLEM.md"  {
            val sim = SimulationController(Robot(tableTop), listOf(
            "PLACE 0,0,NORTH", "LEFT", "REPORT"))
            sim.run()
            sim.lastReport() shouldBe "0,0,WEST"
        }

        "Verify example C in PROBLEM.md"  {
            val sim = SimulationController(Robot(tableTop), listOf(
                    "PLACE 1,2,EAST", "MOVE", "MOVE", "LEFT", "MOVE", "REPORT"))
            sim.run()
            sim.lastReport() shouldBe "3,3,NORTH"
        }

        "Verify moving around the perimeter"  {
            val sim = SimulationController(Robot(tableTop), listOf(
                    "PLACE 0,0,NORTH", "MOVE", "MOVE", "MOVE", "MOVE", "RIGHT",
                    "MOVE", "MOVE", "MOVE", "MOVE", "RIGHT",
                    "MOVE", "MOVE", "MOVE", "MOVE", "RIGHT",
                    "MOVE", "MOVE", "MOVE", "REPORT"))
            sim.run()
            sim.lastReport() shouldBe "1,0,WEST"
        }
    }
}