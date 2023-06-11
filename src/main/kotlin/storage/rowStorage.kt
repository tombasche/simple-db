package storage

import statement.Row

fun allocateRow(pager: Pager, collectionName: String, row: Row) {
    pager.rows[collectionName] = pager.rows[collectionName] ?: mutableListOf()
    pager.rows[collectionName]?.add(serializeRow(row))
}

// TODO don't just fetch everything :D
fun retrieveRows(pager: Pager, collectionName: String): List<Row> {
    val rows = pager.rows[collectionName] ?: return emptyList()
    return rows.map(::deserializeRow)
}

