package storage

import statement.Row

fun serializeRow(row: Row): ByteArray =
    "${row.id},${serializeFields(row.fields)}".toByteArray()

private fun serializeFields(fields: Map<String, *>): String =
    fields.map { (key, value) -> "$key=$value" }.joinToString(",")
