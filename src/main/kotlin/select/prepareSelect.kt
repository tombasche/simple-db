package select

import statement.SelectStatement
import utils.Either
import utils.Success

fun prepareSelect(input: String): Either<SelectStatement> {
    return Success(SelectStatement(
        columns = input.split(" ").filter { it != "select" }
    ))
}