package storage

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DeserializeTests : FunSpec({
    test("deserialize a compact representation into a row") {
        val input = "1,username=cstack,email=foo@bar.com".toByteArray()
        val result = deserializeRow(input)
        result.id shouldBe "1"
        result.fields["username"] shouldBe "cstack"
        result.fields["email"] shouldBe "foo@bar.com"
    }
})