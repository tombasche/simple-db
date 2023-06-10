package storage

import statement.Row

fun allocateRow(table: Table, row: Row) {
    table.rows.add(serializeRow(row))
}

// TODO don't just fetch everything :D
fun retrieveRows(table: Table): List<Row> =
    table.rows.map(::deserializeRow)
