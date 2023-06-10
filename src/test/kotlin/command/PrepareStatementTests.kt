package command

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import statement.InsertStatement
import statement.SelectStatement
import statement.Row

class PrepareStatementTests: FunSpec({
    test("insert input returns insert command") {
        val input = "insert 1 cstack foo@bar.com"
        val result = prepare(input) as InsertStatement
        result.row shouldBe Row(
            id="1",
            fields = emptyMap<String, Any>()
        )
    }

    test("select input returns select command") {
        val input = "select foo bar"
        prepare(input).shouldBeTypeOf<SelectStatement>()
    }

    test("unknown command returns a null") {
        val input = "something diabolical"
        val result = prepare(input)
        result.shouldBeNull()
    }

    test("command must be lowercase") {
        val input = "SELECT foo bar"
        val result = prepare(input)
        result.shouldBeNull()
    }
})