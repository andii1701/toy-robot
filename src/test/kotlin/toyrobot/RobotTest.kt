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

        "Robot().place should set x, y and heading"  {
            val r = Robot(0, 0, Heading.NORTH)
            r.place(1, 2, Heading.SOUTH)
            r.x shouldBe 1
            r.y shouldBe 2
            r.heading shouldBe Heading.SOUTH
        }

        "Robot().report() should return a string containing the report of the robot"  {
            Robot(3,4, Heading.SOUTH).report() shouldBe "3,4 and SOUTH"
        }

        "Commands().turn(currentDir, newDir) should return correct new heading based on current direction"  {
            val r = Robot(0,0, Heading.NORTH)
            r.turn(TurnDirection.LEFT)
            r.heading shouldBe Heading.WEST
            r.turn(TurnDirection.LEFT)
            r.heading shouldBe Heading.SOUTH
            r.turn(TurnDirection.LEFT)
            r.heading shouldBe Heading.EAST
            r.turn(TurnDirection.LEFT)
            r.heading shouldBe Heading.NORTH
            r.turn(TurnDirection.RIGHT)
            r.heading shouldBe Heading.EAST
            r.turn(TurnDirection.RIGHT)
            r.heading shouldBe Heading.SOUTH
            r.turn(TurnDirection.RIGHT)
            r.heading shouldBe Heading.WEST
            r.turn(TurnDirection.RIGHT)
            r.heading shouldBe Heading.NORTH
        }
    }
}