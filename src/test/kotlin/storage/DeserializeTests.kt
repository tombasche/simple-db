package storage

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DeserializeTests: FunSpec({
    test("deserialize a compact representation into a row") {
        val input = "1,cstack,foo@bar.com".toByteArray()
        val result = deserializeRow(input)
        result.id shouldBe "1"
        result.username shouldBe "cstack"
        result.email shouldBe "foo@bar.com"
    }
})