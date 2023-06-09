import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import repl.Command
import repl.prepare

class PrepareStatementTests: FunSpec({
    test("insert input returns insert command") {
        val input = "insert foo bar"
        val result = prepare(input)
        result shouldBe Command.Insert
    }

    test("select input returns select command") {
        val input = "select foo bar"
        val result = prepare(input)
        result shouldBe Command.Select
    }

    test("unknown command returns a null") {
        val input = "something diabolical"
        val result = prepare(input)
        result.shouldBeNull()
    }

    test("command is case insensitive") {
        val input = "SELECT foo bar"
        val result = prepare(input)
        result shouldBe Command.Select
    }
})