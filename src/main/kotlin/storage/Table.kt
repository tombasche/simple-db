package storage
data class Table(
    val rows: MutableMap<String, MutableList<ByteArray>>,
) {
    companion object {
        fun new() = Table(
            rows = mutableMapOf(),
        )
    }
}
