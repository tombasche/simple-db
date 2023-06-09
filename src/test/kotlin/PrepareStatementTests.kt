import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import repl.Command
import repl.prepareStatement

class PrepareStatementTests: FunSpec({
    test("insert input returns insert command") {
        val input = "insert foo bar"
        val result = prepareStatement(input)
        result shouldBe Command.Insert
    }
})