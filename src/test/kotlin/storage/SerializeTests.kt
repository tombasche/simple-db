package storage

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import statement.Row

class SerializeTests: FunSpec({
    test("serialize a row into a compact representation") {
        val row = Row(
            id = "1",
            fields = mapOf(
                "username" to "cstack",
                "email" to "foo@bar.com",
            )
        )
        val result = serializeRow(row)
        result.shouldBeTypeOf<ByteArray>() // Used to make the below assertion nicer to debug
        String(result) shouldBe "1,username=cstack,email=foo@bar.com"
    }
})