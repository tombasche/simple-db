package command

import io.kotest.core.spec.style.FunSpec
import statement.InsertStatement
import statement.SelectStatement
import statement.TempRow
import storage.Table

class ExecuteStatementTests: FunSpec({
    test("it executes a select") {
        execute(
            Table.new(),
            SelectStatement(
                columns = listOf("id", "username", "email")
            )
        )
    }
    test("it executes an insert") {
        execute(
            Table.new(),
            InsertStatement(
                row = TempRow(
                    id = "1",
                    username = "cstack",
                    email = "foo@bar.com"
                )
            )
        )
    }
})