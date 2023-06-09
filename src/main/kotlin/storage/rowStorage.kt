package storage

import statement.TempRow

fun allocateRow(table: Table, row: ByteArray) {
    table.rows.add(row)
}

fun retrieveRows(table: Table): List<TempRow> =
    table.rows.map(::deserializeRow)
