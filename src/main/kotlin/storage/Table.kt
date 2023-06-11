package storage

data class Table(
    val blockStorage: BlockStorage
) {
    companion object {
        fun open(dbName: String) = Table(
            blockStorage = BlockStorage.open(dbName),
        )
    }
}
