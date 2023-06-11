package storage

import java.io.File


data class BlockStorage(
    val file: File,
    val rows: MutableMap<String, MutableList<ByteArray>>,
) {
    companion object {
        fun open(dbName: String) = BlockStorage(
            file = File(dbName),
            rows = mutableMapOf()
        )
    }
}
