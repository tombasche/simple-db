package storage

data class Block(
    val rows: MutableMap<String, MutableList<ByteArray>>,
)
