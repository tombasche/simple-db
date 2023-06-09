package command

import insert.prepareInsert
import select.prepareSelect
import statement.Statement

fun prepare(input: String): Statement? {
    if (input.startsWith("insert")) return prepareInsert(input)
    if (input.startsWith("select")) return prepareSelect(input)
    return null
}
