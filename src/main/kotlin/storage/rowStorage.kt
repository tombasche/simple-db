package storage

import statement.Row

fun allocateRow(table: Table, row: ByteArray) {
    table.rows.add(row)
}

fun retrieveRows(table: Table): List<Row> =
    table.rows.map(::deserializeRow)
