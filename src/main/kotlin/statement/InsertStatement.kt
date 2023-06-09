package statement

data class InsertStatement(
    val row: TempRow
) : Statement()


// TODO - genericise
data class TempRow(
    val id: String,
    val username: String,
    val email: String
)