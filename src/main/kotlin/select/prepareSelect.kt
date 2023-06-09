package select

import statement.SelectStatement
import statement.TempRow

fun prepareSelect(input: String): SelectStatement {
    return SelectStatement(
        TempRow(
            id = "1",
            username = "cstack",
            email = "foo@bar.com"
        )
    )
}