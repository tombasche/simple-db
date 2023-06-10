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
        result.shouldBeTypeOf<ByteArray>()
        // TODO - should serialize into below
//        String(result) shouldBe "1,cstack,foo@bar.com"
    }
})