package storage

import statement.TempRow

fun serializeRow(row: TempRow): ByteArray {
    return "${row.id},${row.username},${row.email}".toByteArray()
}