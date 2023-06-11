package statement

data class SelectStatement(
    val collectionName: String,
) : Statement()