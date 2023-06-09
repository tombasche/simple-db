package insert

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import storage.Table

class ExecuteInsertTests: FunSpec({
    test("executes an insert") {
        val table = Table.new()
        val row = prepareInsert("insert 1 cstack foo@bar.com")

        val result = executeInsert(table, row)
        result shouldBe Unit
    }
})