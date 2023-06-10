package storage

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import statement.Row

class FetchRetrieveIntegrationTests: FunSpec({
    test("insert and select rows") {
        val table = Table.new()
        val row = Row(id="1", fields= mapOf(
            "username" to "cstack",
            "email" to "foo@bar.com"
        ))

        allocateRow(table, row)

        val rows = retrieveRows(table)
        rows shouldBe listOf(
            Row(
                id = "1",
                fields = mapOf(
                    "username" to "cstack",
                    "email" to "foo@bar.com"
                ))
            )
    }
})