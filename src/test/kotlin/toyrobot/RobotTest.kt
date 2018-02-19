package toyrobot

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec


class RobotTest : StringSpec() {
    init {

        val tableTop = TableTop()

        "Robot() should set the correct data"  {
            val r = Robot(tableTop)
            r.place(SimpleVector(0, 1, Heading.NORTH))
            (r.getVec() == SimpleVector(0, 1, Heading.NORTH)) shouldBe true
        }

        "Robot().getVec() should return it's current SimpleVector"  {
            val r = Robot(tableTop)
            r.place(SimpleVector(0, 1, Heading.NORTH))
            (r.getVec() == SimpleVector(0, 1, Heading.NORTH)) shouldBe true
        }

        "Changing the results of Robot().getVec() should not update the robot's SimpleVector"  {
            val r = Robot(tableTop)
            r.place(SimpleVector(0, 1, Heading.NORTH))
            val v = r.getVec()
            v?.x = 3
            v?.y = 4
            v?.heading = Heading.SOUTH
            (r.getVec() == SimpleVector(0, 1, Heading.NORTH)) shouldBe true
        }

        "Robot().place() should set x, y and heading"  {
            val r = Robot(tableTop)
            r.place(SimpleVector(1, 2, Heading.SOUTH))
            (r.getVec() == SimpleVector(1, 2, Heading.SOUTH)) shouldBe true
        }

        "Robot().place() should do nothing if x and y are off tabletop and placed is not called"  {
            val r = Robot(tableTop)
            r.place(SimpleVector(-1, 0, Heading.SOUTH))
            r.placed() shouldBe false
        }

        "Robot().place() should do nothing if x is off tabletop"  {
            val r = Robot(tableTop)
            r.place(SimpleVector(1,2, Heading.EAST))
            r.place(SimpleVector(-1,0, Heading.SOUTH))
            (r.getVec() == SimpleVector(1, 2, Heading.EAST)) shouldBe true
        }

        "Robot().place should do nothing if y is off tabletop"  {
            val r = Robot(tableTop)
            r.place(SimpleVector(1, 2, Heading.EAST))
            r.place(SimpleVector(0, -1, Heading.SOUTH))
            (r.getVec() == SimpleVector(1, 2, Heading.EAST)) shouldBe true
        }

        "Robot() should not update it position if passed simpleVector is changed"  {
            val r = Robot(tableTop)
            val v = SimpleVector(0, 1, Heading.NORTH)
            r.place(v)
            v.x = 3
            v.y = 4
            v.heading = Heading.SOUTH
            (r.getVec() == SimpleVector(0, 1, Heading.NORTH)) shouldBe true
        }

        "Robot().placed() should return false if robot is not placed"  {
            val r = Robot(tableTop)
            r.placed() shouldBe false
        }

        "Robot().placed() should return true if robot is placed"  {
            val r = Robot(tableTop)
            r.place(SimpleVector(0,0, Heading.EAST))
            r.placed() shouldBe true
        }

        "Robot().report() should return a string containing the report of the robot"  {
            val r = Robot(tableTop)
            r.place(SimpleVector(3, 4, Heading.SOUTH))
            r.report() shouldBe "3,4 and SOUTH"
        }

        "Robot().report() should do nothing if called before robot is placed"  {
             val r = Robot(tableTop)
             r.report() shouldBe null
        }

        "Robot().turn(newDir) should return correct new heading based on current direction"  {
            val r = Robot(tableTop)
            r.place(SimpleVector(0,0, Heading.NORTH))
            r.turn(TurnDirection.LEFT)
            r.getVec()?.heading shouldBe Heading.WEST
            r.turn(TurnDirection.LEFT)
            r.getVec()?.heading shouldBe Heading.SOUTH
            r.turn(TurnDirection.LEFT)
            r.getVec()?.heading shouldBe Heading.EAST
            r.turn(TurnDirection.LEFT)
            r.getVec()?.heading shouldBe Heading.NORTH
            r.turn(TurnDirection.RIGHT)
            r.getVec()?.heading shouldBe Heading.EAST
            r.turn(TurnDirection.RIGHT)
            r.getVec()?.heading shouldBe Heading.SOUTH
            r.turn(TurnDirection.RIGHT)
            r.getVec()?.heading shouldBe Heading.WEST
            r.turn(TurnDirection.RIGHT)
            r.getVec()?.heading shouldBe Heading.NORTH
        }

        "Robot().turn(newDir) should do nothing if robot is not placed"  {
            val r = Robot(tableTop)
            r.turn(TurnDirection.LEFT)
            r.placed() shouldBe false
        }

        "Robot().move(...) should set the robot to the correct position and not change the heading"  {
            val r = Robot(tableTop)
            r.place(SimpleVector(1, 2, Heading.NORTH))
            r.move()
            (r.getVec() == SimpleVector(1, 3, Heading.NORTH)) shouldBe true

            r.place(SimpleVector(1, 1, Heading.EAST))
            r.move()
            (r.getVec() == SimpleVector(2, 1, Heading.EAST)) shouldBe true

            r.place(SimpleVector(1, 2, Heading.SOUTH))
            r.move()
            (r.getVec() == SimpleVector(1, 1, Heading.SOUTH)) shouldBe true

            r.place(SimpleVector(1, 0, Heading.WEST))
            r.move()
            (r.getVec() == SimpleVector(0, 0, Heading.WEST)) shouldBe true
        }

        "Robot().move(...) should do nothing if the robot is not placed"  {
            val r = Robot(tableTop)
            r.move()
            r.placed() shouldBe false
        }

        "Robot().move(...) should be ignored if the robot will move off the west edge of the table"  {
            val r = Robot(tableTop)
            r.place(SimpleVector(0, 0, Heading.WEST))
            r.move()
            (r.getVec() == SimpleVector(0, 0, Heading.WEST)) shouldBe true
        }

        "Robot().move(...) should be ignored if the robot will move off the north edge of the table"  {
            val r = Robot(tableTop)
            r.place(SimpleVector(0, 4, Heading.NORTH))
            r.move()
            (r.getVec() == SimpleVector(0, 4, Heading.NORTH)) shouldBe true
        }

        "Robot().move(...) should be ignored if the robot will move off the east edge of the table"  {
            val r = Robot(tableTop)
            r.place(SimpleVector(4, 4, Heading.EAST))
            r.move()
            (r.getVec() == SimpleVector(4, 4, Heading.EAST)) shouldBe true
        }

        "Robot().move(...) should be ignored if the robot will move off the south edge of the table"  {
            val r = Robot(tableTop)
            r.place(SimpleVector(4, 0, Heading.SOUTH))
            r.move()
            (r.getVec() == SimpleVector(4, 0, Heading.SOUTH)) shouldBe true
        }
    }
}