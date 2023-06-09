package insert

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PrepareInsertTests: FunSpec({
    test("args are parsed from the input") {
        val input = "insert 1 cstack foo@bar.com"
        val result = prepareInsert(input)
        result.row.id shouldBe "1"
        result.row.username shouldBe "cstack"
        result.row.email shouldBe "foo@bar.com"
    }
})