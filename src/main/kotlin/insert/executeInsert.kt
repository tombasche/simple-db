package insert

import statement.InsertStatement
import storage.Table
import storage.allocateRow
import storage.serializeRow

fun executeInsert(table: Table, insertStatement: InsertStatement) {
    allocateRow(table, insertStatement.row)
}