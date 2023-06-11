package statement

data class SelectStatement(
    val tableName: String,
    val columns: List<String>,
) : Statement()