package storage

import statement.Row

fun allocateRow(pager: Pager, collectionName: String, row: Row) {
    if (pager.pages.isEmpty())
        pager.pages = mutableListOf(Page(rows = mutableMapOf()))

    // TODO determine if another page is required (ie. it's reached max rows)
    // TODO this should then 'know' the current page to be allocated without having to iterate the whole lot every time
    pager.currentPage().rows[collectionName] = pager.currentPage().rows[collectionName] ?: mutableListOf()
    pager.currentPage().rows[collectionName]?.add(serializeRow(row))
}

fun retrieveRows(pager: Pager, collectionName: String): List<Row> {
    if (pager.pages.isEmpty()) return emptyList()
    val rows = pager.currentPage().rows[collectionName] ?: return emptyList()
    return rows.map(::deserializeRow)
}

