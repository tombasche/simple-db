package storage

import statement.Row

// TODO this needs some love
fun allocateRow(pager: Pager, collectionName: String, row: Row) {
    if (pager.pages.isEmpty())
        pager.pages = mutableListOf(Page(rows = mutableMapOf()))

    pager.pages.first().rows[collectionName] = pager.pages.first().rows[collectionName] ?: mutableListOf()
    pager.pages.first().rows[collectionName]?.add(serializeRow(row))
}

fun retrieveRows(pager: Pager, collectionName: String): List<Row> {
    val pages = pager.pages
    if (pages.isEmpty()) return emptyList()
    val rows = pages.first().rows[collectionName] ?: return emptyList()
    return rows.map(::deserializeRow)
}

