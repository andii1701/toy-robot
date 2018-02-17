package toyrobot

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec


class SimulationControllerIntegrationTests : StringSpec() {
    init {
        "Verify place and report command work" {
            val sim = SimulationController(Robot(), listOf("PLACE 0,0,NORTH", "REPORT"), Commands())
            sim.lastReport() shouldBe "0,0 and NORTH"
        }

        "Verify place, turn left and report command work" {
            val sim = SimulationController(Robot(), listOf("PLACE 0,0,NORTH", "LEFT", "REPORT"), Commands())
            sim.lastReport() shouldBe "0,0 and WEST"
        }

        "Verify place, turn right and report command work" {
            val sim = SimulationController(Robot(), listOf("PLACE 0,0,NORTH", "RIGHT", "REPORT"), Commands())
            sim.lastReport() shouldBe "0,0 and EAST"
        }

        "Verify multiple place commands work"  {
            val robot: Robot? = null
            val sim = SimulationController(Robot(), listOf("PLACE 0,0,NORTH", "PLACE 1,2,EAST", "REPORT"), Commands())
            sim.lastReport() shouldBe "1,2 and EAST"
        }
    }
}