package select

import statement.SelectStatement

fun prepareSelect(input: String): SelectStatement {
    return SelectStatement(
        id = "1",
        username = "cstack",
        email = "foo@bar.com"
    )
}