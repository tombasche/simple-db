package select

import statement.SelectStatement
import utils.Either
import utils.Failure
import utils.Success

fun prepareSelect(input: String): Either<SelectStatement> =
    with(parseCollectionName(input)) {
        when(this) {
            null -> Failure("select statement requires collection name")
            else -> Success(SelectStatement(
                collectionName = this
            ))
        }
    }

fun parseCollectionName(input: String) = input.split(" ").firstOrNull { it != "select" }
