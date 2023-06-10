package select


import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import storage.Table

class ExecuteSelectTests: FunSpec({
    test("executes a select") {
        val table = Table.new()
        val statement = prepareSelect("select 1 cstack foo@bar.com")

        val result = executeSelect(table, statement)
        result shouldBe emptyList()
    }
})