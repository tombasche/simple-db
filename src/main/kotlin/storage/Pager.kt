package storage

import java.io.File

data class Pager(
    val file: File,
    var pages: MutableList<Page>,
) {
    companion object {
        fun open(dbName: String) = Pager(
            file = File(dbName),
            pages = mutableListOf(),
        )
    }
}
