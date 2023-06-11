package storage

import java.io.File

data class Pager(
    val file: File,
    var pages: MutableList<Page>,
    val currentPageNumber: Int,
) {
    companion object {
        fun open(dbName: String) = Pager(
            file = File(dbName),
            pages = mutableListOf(),
            currentPageNumber = 0
        )
    }
}

fun Pager.currentPage(): Page = this.pages[currentPageNumber]
