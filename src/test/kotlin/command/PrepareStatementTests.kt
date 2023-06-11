package command

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.types.shouldBeTypeOf
import statement.InsertStatement
import statement.SelectStatement
import utils.Success

class PrepareStatementTests : FunSpec({
    test("insert input returns insert command") {
        val input = "insert id=1 name=cstack whatever=foo@bar.com users"
        prepare(input).shouldBeTypeOf<Success<InsertStatement>>()
    }

    test("select input returns select command") {
        val input = "select foo bar"
        prepare(input).shouldBeTypeOf<Success<SelectStatement>>()
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