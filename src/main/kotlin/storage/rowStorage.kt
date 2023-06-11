package storage

import statement.Row

fun allocateRow(table: Table, tableName: String, row: Row) {
    table.rows[tableName] = table.rows[tableName] ?: mutableListOf()
    table.rows[tableName]?.add(serializeRow(row))
}

// TODO don't just fetch everything :D
fun retrieveRows(table: Table, tableName: String): List<Row> {
    val rows = table.rows[tableName] ?: return emptyList()
    return rows.map(::deserializeRow)
}

