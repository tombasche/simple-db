package storage

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import statement.Row

class FetchRetrieveIntegrationTests : FunSpec({
    test("insert and select rows") {
        val storage = Storage.open("test.db")
        val collectionName = "users"
        val row = Row(
            id = "1", fields = mapOf(
                "username" to "cstack",
                "email" to "foo@bar.com"
            )
        )

        allocateRow(storage, collectionName, row)

        retrieveRows(storage, collectionName) shouldBe listOf(Row(id = row.id, fields = row.fields))
        storage.clear()
    }
})