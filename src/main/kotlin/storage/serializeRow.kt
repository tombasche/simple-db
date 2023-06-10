package storage

import statement.Row

fun serializeRow(row: Row): ByteArray {
    return "${row.id}".toByteArray()
}