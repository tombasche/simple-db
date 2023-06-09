package storage
data class Table(
    val rows: MutableList<ByteArray>,
) {
    companion object {
        fun new() = Table(
            rows = mutableListOf(),
        )
    }
}
