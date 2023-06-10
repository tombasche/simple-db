package storage

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import statement.Row

class RetrieveRowTests: FunSpec({
    test("retrieve all rows") {
        val table = Table(rows = mutableListOf("1,cstack,foo@bar.com".toByteArray()))

        val result = retrieveRows(table)

        result shouldBe listOf(
            Row(
                id = "1",
                fields = emptyMap<String, Any>()
            )
        )
    }
})