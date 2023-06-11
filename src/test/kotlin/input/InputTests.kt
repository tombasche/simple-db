package input

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import repl.input.clean

class InputTests : FunSpec({
    test("extra spaces are removed from the input") {
        val rawInput = "  select   "
        val result = clean(rawInput)
        result shouldBe "select"
    }
    test("casing is normalised") {
        val rawInput = "sElEcT"
        val result = clean(rawInput)
        result shouldBe "select"
    }
    test("empty string is treated as a null") {
        val rawInput = ""
        val result = clean(rawInput)
        result.shouldBeNull()
    }
    test("just whitespace is treated as a null") {
        val rawInput = "        "
        val result = clean(rawInput)
        result.shouldBeNull()
    }
})