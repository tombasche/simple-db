package storage

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import statement.TempRow

class RetrieveRowTests: FunSpec({
    test("retrieve all rows") {
        val table = Table(rows = mutableListOf("1,cstack,foo@bar.com".toByteArray()))

        val result = retrieveRows(table)

        result shouldBe listOf(
            TempRow(
                id = "1",
                username = "cstack",
                email = "foo@bar.com"
            )
        )
    }
})