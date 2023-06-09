package command

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import statement.InsertStatement
import statement.SelectStatement
import statement.TempRow

class PrepareStatementTests: FunSpec({
    test("insert input returns insert command") {
        val input = "insert 1 cstack foo@bar.com"
        val result = prepare(input) as InsertStatement
        result.row shouldBe TempRow(
            id="1",
            username="cstack",
            email="foo@bar.com"
        )
    }

    test("select input returns select command") {
        val input = "select foo bar"
        val result = prepare(input) as SelectStatement
        result.id shouldBe "1"
        result.username shouldBe "cstack"
        result.email shouldBe "foo@bar.com"
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