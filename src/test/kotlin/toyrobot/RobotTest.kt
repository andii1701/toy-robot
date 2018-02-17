package toyrobot

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec


class RobotTest : StringSpec() {
    init {
        "Robot(..) should set the correct data"  {
            val r = Robot(0, 1, Heading.NORTH)
            r.x shouldBe 0
            r.y shouldBe 1
            r.heading shouldBe Heading.NORTH
        }
    }
}