package storage

import statement.Row

fun allocateRow(pager: Pager, collectionName: String, row: Row) {
    if (pager.reachedMax()) {
        pager.newPage()
    }
    val currentPage = pager.currentPage()
    currentPage.rows[collectionName] = currentPage.rows[collectionName] ?: mutableListOf()
    currentPage.rows[collectionName]?.add(serializeRow(row))
}

fun retrieveRows(pager: Pager, collectionName: String): List<Row> {
    val rows = pager.currentPage().rows[collectionName] ?: return emptyList()
    return rows.map(::deserializeRow)
}

