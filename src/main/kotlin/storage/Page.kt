package storage

data class Page(
    val rows: MutableMap<String, MutableList<ByteArray>>,
)
