package toyrobot

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec


class CoordinateTests : StringSpec() {
    init {
        "Coordinate(x,y) should set the x and y values"  {
            val c = Coordinate(0,1)
            c.x shouldBe 0
            c.y shouldBe 1
        }

        "Coordinate(x,y) x and y can be negative"  {
            val c = Coordinate(-1,-2)
            c.x shouldBe -1
            c.y shouldBe -2
        }
    }
}