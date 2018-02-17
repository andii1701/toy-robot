package toyrobot

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec


class SimulationControllerIntegrationTests : StringSpec() {
    init {
        val tableTop = TableTop(5,5)
        "Verify place and report command work" {
            val sim = SimulationController(Robot(tableTop), listOf("PLACE 0,0,NORTH", "REPORT"), Commands())
            sim.lastReport() shouldBe "0,0 and NORTH"
        }

        "Verify place, turn left and report command work" {
            val sim = SimulationController(Robot(tableTop), listOf("PLACE 0,0,NORTH", "LEFT", "REPORT"), Commands())
            sim.lastReport() shouldBe "0,0 and WEST"
        }

        "Verify place, turn right and report command work" {
            val sim = SimulationController(Robot(tableTop), listOf("PLACE 0,0,NORTH", "RIGHT", "REPORT"), Commands())
            sim.lastReport() shouldBe "0,0 and EAST"
        }

        "Verify multiple place commands work"  {
            val sim = SimulationController(Robot(tableTop), listOf("PLACE 0,0,NORTH", "PLACE 1,2,EAST", "REPORT"), Commands())
            sim.lastReport() shouldBe "1,2 and EAST"
        }

        "Verify place, move and report works"  {
            val sim = SimulationController(Robot(tableTop), listOf("PLACE 0,0,NORTH", "MOVE", "REPORT"), Commands())
            sim.lastReport() shouldBe "0,1 and NORTH"
        }

        "Verify commands are ignored before the place command"  {}
    }
}