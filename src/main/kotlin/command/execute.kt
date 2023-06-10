package command

import insert.executeInsert
import select.executeSelect
import statement.InsertStatement
import statement.SelectStatement
import statement.Statement
import storage.Table

fun execute(table: Table, statement: Statement): ExecuteResult<*> {
    return when(statement) {
        is InsertStatement -> {
            executeInsert(table, statement)
            ExecuteResult("Inserted row ${statement.row}")
        }
        is SelectStatement -> {
            val result = executeSelect(table, statement)
            ExecuteResult(result)
        }
    }
}

data class ExecuteResult<T>(
    val result: T
)