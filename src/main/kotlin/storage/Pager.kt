package storage

import java.io.File


data class Pager(
    val file: File,
    var pages: MutableList<Page>,
    var currentPageNumber: Int,
) {
    companion object {
        fun open(dbName: String) = Pager(
            file = File(dbName),
            pages = mutableListOf(
                Page(rows = mutableMapOf())
            ),
            currentPageNumber = 0
        )
        const val MAX_ROWS_PER_PAGE = 100
    }
}

fun Pager.currentPage(): Page = this.pages[currentPageNumber]
fun Pager.reachedMax(): Boolean = this.pages[currentPageNumber].rows.values.flatten().size >= Pager.MAX_ROWS_PER_PAGE
fun Pager.newPage() {
    this.currentPageNumber += 1
    this.pages += Page(rows = mutableMapOf())
}
