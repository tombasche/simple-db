package storage

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import statement.TempRow

class SerializeTests: FunSpec({
    test("serialize a row into a compact representation") {
        val row = TempRow(
            id = "1",
            username = "cstack",
            email = "foo@bar.com"
        )
        val result = serializeRow(row)
        result.shouldBeTypeOf<ByteArray>()
        String(result) shouldBe "1,cstack,foo@bar.com"
    }
})