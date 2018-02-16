import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import toyrobot.TableTop

class TableTopTests : StringSpec() {
    init {
        "TableTop(length=5).length should be 5"  {
            TableTop(xLength=5).xLength shouldBe 5
        }

    }
}