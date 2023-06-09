package command

import insert.executeInsert
import statement.InsertStatement
import statement.SelectStatement
import statement.Statement
import storage.Table

fun execute(table: Table, statement: Statement) {
    when(statement) {
        is InsertStatement -> executeInsert(table, statement)
        is SelectStatement -> println("selecting $statement ")
    }
}