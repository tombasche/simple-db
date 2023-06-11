package storage

import statement.Row

fun allocateRow(pager: Pager, collectionName: String, row: Row) {
    if (pager.reachedMax()) {
        pager.newPage()
    }
    pager.currentPage().rows[collectionName] = pager.currentPage().rows[collectionName] ?: mutableListOf()
    pager.currentPage().rows[collectionName]?.add(serializeRow(row))
}

fun retrieveRows(pager: Pager, collectionName: String): List<Row> {
    if (pager.pages.isEmpty()) return emptyList()
    val rows = pager.currentPage().rows[collectionName] ?: return emptyList()
    return rows.map(::deserializeRow)
}

