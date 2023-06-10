package select

import statement.SelectStatement
import statement.TempRow
import storage.Table
import storage.retrieveRows

fun executeSelect(table: Table, statement: SelectStatement): List<TempRow> =
    retrieveRows(table)