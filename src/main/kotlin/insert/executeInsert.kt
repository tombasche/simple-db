package insert

import statement.InsertStatement
import storage.Table
import storage.allocateRow

fun executeInsert(table: Table, insertStatement: InsertStatement) {
    allocateRow(table.pager, insertStatement.collectionName, insertStatement.row)
}