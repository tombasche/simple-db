package storage

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import statement.Row

class FetchRetrieveIntegrationTests: FunSpec({
    test("insert and select rows") {
        val table = Table.new()
        val row = "1,cstack,foo@bar.com".toByteArray()

        allocateRow(table, row)

        val rows = retrieveRows(table)
        rows shouldBe listOf(
            Row(
                id = "1",
                fields = emptyMap<String, Any>()
            )
        )

    }
})