package storage

import statement.TempRow

fun deserializeRow(input: ByteArray): TempRow {
    val parts = String(input).split(",")
    return TempRow(
        id = parts[0],
        username = parts[1],
        email = parts[2]
    )
}