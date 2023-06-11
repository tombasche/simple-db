package storage

import statement.Row

fun allocateRow(table: Table, collectionName: String, row: Row) {
    table.rows[collectionName] = table.rows[collectionName] ?: mutableListOf()
    table.rows[collectionName]?.add(serializeRow(row))
}

// TODO don't just fetch everything :D
fun retrieveRows(table: Table, collectionName: String): List<Row> {
    val rows = table.rows[collectionName] ?: return emptyList()
    return rows.map(::deserializeRow)
}

