import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

import toyrobot.SimulationController

class SimulationControllerTests : StringSpec() {
    init {
        "Validate commands are in correct format" {
            SimulationController().validateCommands(listOf("PLACE 0,0,NORTH", "MOVE", "LEFT", "RIGHT", "REPORT")) shouldBe true
        }
    }
}