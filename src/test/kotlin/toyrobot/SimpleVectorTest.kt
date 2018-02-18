package toyrobot

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec


class SimpleVectorTests : StringSpec() {
    init {
        "SimpleVector(x, y, h) should set the x, y and headingvalues"  {
            val v = SimpleVector(0,1, Heading.EAST)
            v.x shouldBe 0
            v.y shouldBe 1
            v.heading shouldBe Heading.EAST
        }

        "SimpleVector(x, y, h) x and y can be negative"  {
            val c = SimpleVector(-1,-2, Heading.SOUTH)
            c.x shouldBe -1
            c.y shouldBe -2
            c.heading shouldBe Heading.SOUTH
        }
    }
}