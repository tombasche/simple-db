package storage

import statement.Row

fun allocateRow(blockStorage: BlockStorage, collectionName: String, row: Row) {
    blockStorage.rows[collectionName] = blockStorage.rows[collectionName] ?: mutableListOf()
    blockStorage.rows[collectionName]?.add(serializeRow(row))
}

fun retrieveRows(blockStorage: BlockStorage, collectionName: String): List<Row> {
    val rows = blockStorage.rows[collectionName] ?: return emptyList()
    return rows.map(::deserializeRow)
}
