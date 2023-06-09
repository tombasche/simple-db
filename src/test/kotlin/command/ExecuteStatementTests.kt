package command

import io.kotest.core.spec.style.FunSpec
import repl.command.Command
import repl.command.execute

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