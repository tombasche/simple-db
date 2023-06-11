package storage

import java.io.File


data class BlockStorage(
    val file: File,
    val rows: MutableMap<String, MutableList<ByteArray>>,
) {
    companion object {
        fun open(dbName: String) = BlockStorage(
            file = getOrCreateDbFile(File(dbName)),
            rows = mutableMapOf()
        )
    }
}

private fun getOrCreateDbFile(dbFile: File) =
    when(dbFile.exists()) {
        true -> dbFile
        false -> dbFile.createNewFile().let { dbFile }
    }

fun BlockStorage.flush() {
    rows.forEach {
        (k, v) ->
        file.appendBytes("$k:".toByteArray())
        v.forEach{
            file.appendBytes(it)
        }
    }
    rows.clear()
}
