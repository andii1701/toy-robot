package toyrobot

import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
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

        "Robot().placed() should return true if x, y and heading are set"  {
            val r = Robot()
            r.placed() shouldBe false
            r.x = 1
            r.placed() shouldBe false
            r.y = 2
            r.placed() shouldBe false
            r.heading = Heading.SOUTH
            r.placed() shouldBe true
        }

        "Robot().report() should return a string containing the report of the robot"  {
            Robot(3,4, Heading.SOUTH).report() shouldBe "3,4 and SOUTH"
        }

        "Robot().report() should throw an exception if called before being placed"  {
            shouldThrow<UnexpectedCommandException> { Robot().report()}
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