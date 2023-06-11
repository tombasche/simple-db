package storage

import statement.Row

fun deserializeRow(input: ByteArray): Row {
    val parts = String(input).split(",")
    val fields = parts.slice(1 until parts.size)
    return Row(
        id = parts[0],
        fields = deserializeFields(fields)
    )
}

private fun deserializeFields(fields: List<String>): Map<String, *> =
    fields.map { it.split("=") }.associate { (key, value) -> key to value }