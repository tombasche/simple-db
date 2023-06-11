package storage

import statement.Row

fun allocateRow(blockStorage: BlockStorage, collectionName: String, row: Row) {
    blockStorage.rows[collectionName] = blockStorage.rows[collectionName] ?: mutableListOf()
    blockStorage.rows[collectionName]?.add(serializeRow(row))
}

fun retrieveRows(blockStorage: BlockStorage, collectionName: String): List<Row> =
    with(blockStorage.rows[collectionName] ?: emptyList()) {
        this.map(::deserializeRow)
    }
