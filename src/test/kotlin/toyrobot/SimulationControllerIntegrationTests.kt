package toyrobot

import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.StringSpec


class SimulationControllerIntegrationTests : StringSpec() {
    init {
        """Commands:
            |PLACE 0,0,NORTH
            |REPORT
            |should report the robot is at 0,0,NORTH""" {
            val robot: Robot? = null
            val sim = SimulationController(robot, listOf("PLACE 0,0,NORTH", "REPORT"), Commands())
            sim.lastReport() shouldBe "0,0 and NORTH"
        }
    }
}