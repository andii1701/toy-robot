import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.StringSpec

import toyrobot.TableTop
import toyrobot.TableTopException


class TableTopTests : StringSpec() {
    init {

        "TableTop() should set xLength to 5 and yLength to 5 by default"  {
            val t = TableTop()
            t.xLength shouldBe 5
            t.yLength shouldBe 5
        }

        "TableTop(xLength = 7, yLength = 6) should set xLength to 7 and yLength to 6"  {
            val t = TableTop(xLength = 7, yLength = 6)
            t.xLength shouldBe 7
            t.yLength shouldBe 6
        }

        "TableTop() xLength and yLength values should not be negative"  {
            shouldThrow<TableTopException> { TableTop(xLength = -1, yLength = 0) }
            shouldThrow<TableTopException> { TableTop(xLength = 0, yLength = -1) }
        }

        "TableTop(...).onTable(x, y) method should return true if x and y are coordinates are on the table" {
            val t = TableTop(xLength = 5, yLength = 5)
            t.onTable(0,0) shouldBe true
            t.onTable(4,4) shouldBe true
            t.onTable(4,5) shouldBe false
            t.onTable(5,4) shouldBe false
            t.onTable(-1,0) shouldBe false
            t.onTable(0,-1) shouldBe false
            t.onTable(-1,-1) shouldBe false
        }

    }
}