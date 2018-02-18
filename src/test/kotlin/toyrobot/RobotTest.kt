package toyrobot

import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.StringSpec


class RobotTest : StringSpec() {
    init {

        val tableTop = TableTop(5, 5)

        "Robot(..) should set the correct data"  {
            val r = Robot(tableTop)
            r.place(0, 1, Heading.NORTH)
            // TODO these three lines below should be replaced with a Vector or simulaor class
            r.x shouldBe 0
            r.y shouldBe 1
            r.heading shouldBe Heading.NORTH
        }

        "Robot().place should set x, y and heading"  {
            val r = Robot(tableTop)
            r.place(1, 2, Heading.SOUTH)
            r.x shouldBe 1
            r.y shouldBe 2
            r.heading shouldBe Heading.SOUTH
        }

        "Robot().place should do nothing if x and y are off tabletop and place is not called"  {
            val r = Robot(tableTop)
            r.place(-1, -2, Heading.SOUTH)
            r.placed() shouldBe false
        }

        "Robot().place should do nothing if x is off tabletop"  {
            val r = Robot(tableTop)
            r.place(1, 2, Heading.EAST)
            r.place(5, 0, Heading.SOUTH)
            r.x shouldBe 1
            r.y shouldBe 2
            r.heading shouldBe Heading.EAST
        }

        "Robot().place should do nothing if y is off tabletop"  {
            val r = Robot(tableTop)
            r.place(1, 2, Heading.EAST)
            r.place(0, 5, Heading.SOUTH)
            r.x shouldBe 1
            r.y shouldBe 2
            r.heading shouldBe Heading.EAST
        }


        "Robot().placed() should return true if x, y and heading are set"  {
            val r = Robot(tableTop)
            r.placed() shouldBe false
            r.x = 1
            r.placed() shouldBe false
            r.y = 2
            r.placed() shouldBe false
            r.heading = Heading.SOUTH
            r.placed() shouldBe true
        }

        "Robot().report() should return a string containing the report of the robot"  {
            val r = Robot(tableTop)
            r.place(3,4, Heading.SOUTH)
            r.report() shouldBe "3,4 and SOUTH"
        }

        "Robot().report() should throw an exception if called before being placed"  {
            shouldThrow<UnexpectedCommandException> { Robot(tableTop).report()}
        }

        "Robot().turn(newDir) should return correct new heading based on current direction"  {
            val r = Robot(tableTop)
            r.place(0,0, Heading.NORTH)
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

        "Robot().move(...) should set the robot to the correct position and not change the heading"  {
            val r = Robot(tableTop)
            r.place(1,1, Heading.NORTH)
            r.move()
            r.x shouldBe 1
            r.y shouldBe 2
            r.heading shouldBe Heading.NORTH

            r.place(1,1, Heading.EAST)
            r.move()
            r.x shouldBe 2
            r.y shouldBe 1
            r.heading shouldBe Heading.EAST

            r.place(1,1, Heading.SOUTH)
            r.move()
            r.x shouldBe 1
            r.y shouldBe 0
            r.heading shouldBe Heading.SOUTH

            r.place(1,1, Heading.WEST)
            r.move()
            r.x shouldBe 0
            r.y shouldBe 1
            r.heading shouldBe Heading.WEST
        }

        "Robot().move(...) should be ignored if the robot will move off the west edge of the table"  {
            val r = Robot(tableTop)
            r.place(0, 0, Heading.WEST)
            r.move()
            r.x shouldBe 0
            r.y shouldBe 0
            r.heading shouldBe Heading.WEST
        }

        "Robot().move(...) should be ignored if the robot will move off the north edge of the table"  {
            val r = Robot(tableTop)
            r.place(0, 4, Heading.NORTH)
            r.move()
            r.x shouldBe 0
            r.y shouldBe 4
            r.heading shouldBe Heading.NORTH
        }

        "Robot().move(...) should be ignored if the robot will move off the east edge of the table"  {
            val r = Robot(tableTop)
            r.place(4, 4, Heading.EAST)
            r.move()
            r.x shouldBe 4
            r.y shouldBe 4
            r.heading shouldBe Heading.EAST
        }

        "Robot().move(...) should be ignored if the robot will move off the south edge of the table"  {
            val r = Robot(tableTop)
            r.place(4, 0, Heading.SOUTH)
            r.move()
            r.x shouldBe 4
            r.y shouldBe 0
            r.heading shouldBe Heading.SOUTH
        }
    }
}