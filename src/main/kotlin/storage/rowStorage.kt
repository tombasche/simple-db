package storage

import statement.Row

fun allocateRow(storage: Storage, collectionName: String, row: Row) {
    storage.rows[collectionName] = storage.rows[collectionName] ?: mutableListOf()
    storage.rows[collectionName]?.add(serializeRow(row))
}

fun retrieveRows(storage: Storage, collectionName: String): List<Row> =
    with(storage.rows[collectionName] ?: emptyList()) {
        this.map(::deserializeRow)
    }
