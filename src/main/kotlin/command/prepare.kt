package command

import insert.prepareInsert
import select.prepareSelect
import statement.Statement
import utils.Either

fun prepare(input: String): Either<Statement>? {
    if (input.startsWith("insert")) return prepareInsert(input)
    if (input.startsWith("select")) return prepareSelect(input)
    return null
}
