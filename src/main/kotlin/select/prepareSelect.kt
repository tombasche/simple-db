package select

import statement.SelectStatement
import utils.Either
import utils.Success

fun prepareSelect(input: String): Either<SelectStatement> {
    return Success(SelectStatement(
        tableName = "TODO",
        columns = input.split(" ").filter { it != "select" }
    ))
}