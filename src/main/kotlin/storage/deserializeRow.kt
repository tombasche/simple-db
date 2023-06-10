package storage

import statement.Row

fun deserializeRow(input: ByteArray): Row {
    val parts = String(input).split(",")
    return Row(
        id = parts[0],
        fields = emptyMap<String, String>(), // TODO
    )
}