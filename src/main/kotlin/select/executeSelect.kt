package select

import statement.SelectStatement
import statement.Row
import storage.Table
import storage.retrieveRows

fun executeSelect(table: Table, statement: SelectStatement): List<Row> =
    retrieveRows(table, statement.tableName)