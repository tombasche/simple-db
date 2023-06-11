package select

import statement.Row
import statement.SelectStatement
import storage.Table
import storage.retrieveRows

fun executeSelect(table: Table, statement: SelectStatement): List<Row> =
    retrieveRows(table.storage, statement.collectionName)