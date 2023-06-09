package command

import io.kotest.core.spec.style.FunSpec
import command.Command
import command.execute
import statement.InsertStatement
import statement.SelectStatement
import statement.TempRow

class ExecuteStatementTests: FunSpec({
    test("it executes a select") {
        execute(SelectStatement(
            TempRow(
                id = "1",
                username = "cstack",
                email = "foo@bar.com"
            )

        ))
    }
    test("it executes an insert") {
        execute(InsertStatement(
            row = TempRow(
                id = "1",
                username = "cstack",
                email = "foo@bar.com"
            )
        ))
    }
})