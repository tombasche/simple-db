import io.kotest.core.spec.style.FunSpec
import repl.Command
import repl.execute

class ExecuteStatementTests: FunSpec({
    test("it executes a select") {
        val input = Command.Select
        execute(input)
    }
    test("it executes an insert") {
        val input = Command.Insert
        execute(input)
    }
})