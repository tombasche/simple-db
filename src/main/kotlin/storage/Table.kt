package storage

import java.io.File

data class Table(
    val pager: Pager
) {
    companion object {
        fun open(dbName: String) = Table(
            pager = Pager.open(dbName),
        )
    }
}
