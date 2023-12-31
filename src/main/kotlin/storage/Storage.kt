package storage

import java.io.File


data class Storage(
    val file: File,
    val rows: MutableMap<String, MutableList<ByteArray>>,
) {
    companion object {
        fun open(dbName: String): Storage =
            with(
                Storage(
                    file = getOrCreateDbFile(File(dbName)),
                    rows = mutableMapOf()
                )
            ) {
                load()
                this
            }
    }
}

private fun getOrCreateDbFile(dbFile: File) =
    when (dbFile.exists()) {
        true -> dbFile
        false -> dbFile.createNewFile().let { dbFile }
    }

fun Storage.flush() {
    fun formatRow(tableName: String, row: ByteArray) = "$tableName:".toByteArray() + row + "\n".toByteArray()

    rows.forEach { (k, v) ->
        v.forEach {
            file.appendBytes(formatRow(k, it))
        }
    }
    rows.clear()
}

fun Storage.load() {
    file.readLines().forEach { line ->
        line.split(":").let {
            val tableName = it.first()
            val row = deserializeRow(it[1].toByteArray())
            rows[tableName] = rows[tableName] ?: mutableListOf()
            rows[tableName]?.add(serializeRow(row))
        }
    }
}

fun Storage.clear() = rows.clear().also {
    if (file.exists()) file.delete() else Unit
}
