package select

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import utils.Success

class PrepareSelectTests: FunSpec({
    test("args are parsed from the input") {
        val input = "select id username email"
        val result = (prepareSelect(input) as Success).value
        result.columns shouldBe listOf("id", "username", "email")
    }
})