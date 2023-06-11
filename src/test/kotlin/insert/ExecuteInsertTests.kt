package insert

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import storage.Table
import utils.Success

class ExecuteInsertTests: FunSpec({
    test("executes an insert") {
        val table = Table.open("test.db")
        val row = (prepareInsert("insert id=1 name=cstack email=foo@bar.com users") as Success).value

        val result = executeInsert(table, row)
        result shouldBe Unit
    }
})