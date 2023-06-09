package statement

data class SelectStatement(
    val columns: List<String>,
) : Statement()