package toyrobot

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class SimpleVectorTests : StringSpec() {
    init {
        "SimpleVector(x, y, h) should set the x, y and heading values" {
            val v = SimpleVector(0, 1, Heading.EAST)
            (v == SimpleVector(0, 1, Heading.EAST)) shouldBe true
        }

        "SimpleVector(x, y, h) check that x and y can be negative" {
            val v = SimpleVector(-1, -2, Heading.SOUTH)
            (v == SimpleVector(-1, -2, Heading.SOUTH)) shouldBe true
        }

        "SimpleVector(x, y, h) == SimpleVector(x, y, h) should true of all parameters of the 2 vectors are equal" {
            (SimpleVector(0, 1, Heading.EAST) == SimpleVector(0, 1, Heading.EAST)) shouldBe true
        }

        "SimpleVector(x, y, h) == SimpleVector(w, y, h)) should return false if any of the parameters of the 2 vectors are different" {
            (SimpleVector(0, 1, Heading.EAST) == SimpleVector(1, 1, Heading.EAST)) shouldBe false
        }

        "SimpleVector().move() should move NORTH if the heading NORTH" {
            val v = SimpleVector(0, 0, Heading.NORTH)
            v.move()
            (v == SimpleVector(0, 1, Heading.NORTH)) shouldBe true
        }

        "SimpleVector().move() should move EAST if the heading EAST" {
            val v = SimpleVector(0, 0, Heading.EAST)
            v.move()
            (v == SimpleVector(1, 0, Heading.EAST)) shouldBe true
        }

        "SimpleVector().move() should move SOUTH if the heading is SOUTH" {
            val v = SimpleVector(0, 0, Heading.SOUTH)
            v.move()
            (v == SimpleVector(0, -1, Heading.SOUTH)) shouldBe true
        }

        "SimpleVector().move() should move WEST if the heading is WEST" {
            val v = SimpleVector(0, 0, Heading.WEST)
            v.move()
            (v == SimpleVector(-1, 0, Heading.WEST)) shouldBe true
        }

        "SimpleVector.turn(newDir) should return new heading based on current heading" {
            val v = SimpleVector(0, 0, Heading.NORTH)
            v.turn(TurnDirection.LEFT)
            v.heading shouldBe Heading.WEST
            v.turn(TurnDirection.LEFT)
            v.heading shouldBe Heading.SOUTH
            v.turn(TurnDirection.LEFT)
            v.heading shouldBe Heading.EAST
            v.turn(TurnDirection.LEFT)
            v.heading shouldBe Heading.NORTH
            v.turn(TurnDirection.RIGHT)
            v.heading shouldBe Heading.EAST
            v.turn(TurnDirection.RIGHT)
            v.heading shouldBe Heading.SOUTH
            v.turn(TurnDirection.RIGHT)
            v.heading shouldBe Heading.WEST
            v.turn(TurnDirection.RIGHT)
            v.heading shouldBe Heading.NORTH
        }
    }
}