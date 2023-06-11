package storage

data class Table(
    val storage: Storage
) {
    companion object {
        fun open(dbName: String) = Table(
            storage = Storage.open(dbName),
        )
    }
}
