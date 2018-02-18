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
            val v = SimpleVector(-1,-2, Heading.SOUTH)
            v.x shouldBe -1
            v.y shouldBe -2
            v.heading shouldBe Heading.SOUTH
        }

        "SimpleVector().move() should move NORTH if the direction is NORTH" {
            val v = SimpleVector(0,0, Heading.NORTH)
            v.move()
            v.x shouldBe 0
            v.y shouldBe 1
            v.heading shouldBe Heading.NORTH
        }

        "SimpleVector().move() should move EAST if the direction is EAST" {
            val v = SimpleVector(0,0, Heading.EAST)
            v.move()
            v.x shouldBe 1
            v.y shouldBe 0
            v.heading shouldBe Heading.EAST
        }

        "SimpleVector().move() should move SOUTH if the direction is SOUTH" {
            val v = SimpleVector(0,0, Heading.SOUTH)
            v.move()
            v.x shouldBe 0
            v.y shouldBe -1
            v.heading shouldBe Heading.SOUTH
        }

        "SimpleVector().move() should move WEST if the direction is WEST" {
            val v = SimpleVector(0,0, Heading.WEST)
            v.move()
            v.x shouldBe -1
            v.y shouldBe 0
            v.heading shouldBe Heading.WEST
        }
    }
}