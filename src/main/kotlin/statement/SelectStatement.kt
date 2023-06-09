package statement

// TODO make it generic
data class SelectStatement(
    val id: String,
    val username: String,
    val email: String
) : Statement()