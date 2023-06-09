package statement

// TODO make it generic
data class SelectStatement(
    val row: TempRow,
) : Statement()