package select


import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import statement.SelectStatement
import storage.Table

class ExecuteSelectTests: FunSpec({
    test("executes a select") {
        val table = Table.new()
        val statement = SelectStatement(
            collectionName = "users",
        )

        val result = executeSelect(table, statement)
        result shouldBe emptyList()
    }
})