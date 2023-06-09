package select

import statement.SelectStatement

fun prepareSelect(input: String): SelectStatement {
    return SelectStatement(
        columns = input.split(" ").filter { it != "select" }
    )
}