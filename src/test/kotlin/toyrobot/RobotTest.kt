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
            r.getX() shouldBe 0
            r.getY() shouldBe 1
            r.getHeading() shouldBe Heading.NORTH
        }

        "Robot().place should set x, y and heading"  {
            val r = Robot(tableTop)
            r.place(1, 2, Heading.SOUTH)
            r.getX() shouldBe 1
            r.getY() shouldBe 2
            r.getHeading() shouldBe Heading.SOUTH
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
            r.getX() shouldBe 1
            r.getY() shouldBe 2
            r.getHeading() shouldBe Heading.EAST
        }

        "Robot().place should do nothing if y is off tabletop"  {
            val r = Robot(tableTop)
            r.place(1, 2, Heading.EAST)
            r.place(0, 5, Heading.SOUTH)
            r.getX() shouldBe 1
            r.getY() shouldBe 2
            r.getHeading() shouldBe Heading.EAST
        }


        "Robot().placed() should return true if x, y and heading are set"  {
            val r = Robot(tableTop)
            r.placed() shouldBe false
            r.place(1, 2, Heading.SOUTH)
            r.placed() shouldBe true
        }

        "Robot().report() should return a string containing the report of the robot"  {
            val r = Robot(tableTop)
            r.place(3,4, Heading.SOUTH)
            r.report() shouldBe "3,4 and SOUTH"
        }

        "Robot().report() should do nothing if called before robot is placed"  {
             val r = Robot(tableTop)
             r.report() shouldBe null
        }

        "Robot().turn(newDir) should return correct new heading based on current direction"  {
            val r = Robot(tableTop)
            r.place(0,0, Heading.NORTH)
            r.turn(TurnDirection.LEFT)
            r.getHeading() shouldBe Heading.WEST
            r.turn(TurnDirection.LEFT)
            r.getHeading() shouldBe Heading.SOUTH
            r.turn(TurnDirection.LEFT)
            r.getHeading() shouldBe Heading.EAST
            r.turn(TurnDirection.LEFT)
            r.getHeading() shouldBe Heading.NORTH
            r.turn(TurnDirection.RIGHT)
            r.getHeading() shouldBe Heading.EAST
            r.turn(TurnDirection.RIGHT)
            r.getHeading() shouldBe Heading.SOUTH
            r.turn(TurnDirection.RIGHT)
            r.getHeading() shouldBe Heading.WEST
            r.turn(TurnDirection.RIGHT)
            r.getHeading() shouldBe Heading.NORTH
        }

        "Robot().move(...) should set the robot to the correct position and not change the heading"  {
            val r = Robot(tableTop)
            r.place(1,1, Heading.NORTH)
            r.move()
            r.getX() shouldBe 1
            r.getY() shouldBe 2
            r.getHeading() shouldBe Heading.NORTH

            r.place(1,1, Heading.EAST)
            r.move()
            r.getX() shouldBe 2
            r.getY() shouldBe 1
            r.getHeading() shouldBe Heading.EAST

            r.place(1,1, Heading.SOUTH)
            r.move()
            r.getX() shouldBe 1
            r.getY() shouldBe 0
            r.getHeading() shouldBe Heading.SOUTH

            r.place(1,1, Heading.WEST)
            r.move()
            r.getX() shouldBe 0
            r.getY() shouldBe 1
            r.getHeading() shouldBe Heading.WEST
        }

        "Robot().move(...) should do nothing if the robot is not placed"  {
            val r = Robot(tableTop)
            r.move()
            r.placed() shouldBe false
        }

        "Robot().move(...) should be ignored if the robot will move off the west edge of the table"  {
            val r = Robot(tableTop)
            r.place(0, 0, Heading.WEST)
            r.move()
            r.getX() shouldBe 0
            r.getY() shouldBe 0
            r.getHeading() shouldBe Heading.WEST
        }

        "Robot().move(...) should be ignored if the robot will move off the north edge of the table"  {
            val r = Robot(tableTop)
            r.place(0, 4, Heading.NORTH)
            r.move()
            r.getX() shouldBe 0
            r.getY() shouldBe 4
            r.getHeading() shouldBe Heading.NORTH
        }

        "Robot().move(...) should be ignored if the robot will move off the east edge of the table"  {
            val r = Robot(tableTop)
            r.place(4, 4, Heading.EAST)
            r.move()
            r.getX() shouldBe 4
            r.getY() shouldBe 4
            r.getHeading() shouldBe Heading.EAST
        }

        "Robot().move(...) should be ignored if the robot will move off the south edge of the table"  {
            val r = Robot(tableTop)
            r.place(4, 0, Heading.SOUTH)
            r.move()
            r.getX() shouldBe 4
            r.getY() shouldBe 0
            r.getHeading() shouldBe Heading.SOUTH
        }
    }
}