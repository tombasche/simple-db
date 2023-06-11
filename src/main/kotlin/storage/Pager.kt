package storage

import java.io.File

data class Pager(
    val file: File,
    val rows: MutableMap<String, MutableList<ByteArray>>,
) {
    companion object {
        fun open(dbName: String) = Pager(
            file = File(dbName),
            rows = mutableMapOf(),
        )
    }
}
