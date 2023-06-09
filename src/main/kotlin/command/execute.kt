package command

import statement.InsertStatement
import statement.SelectStatement
import statement.Statement

fun execute(statement: Statement) {
    when(statement) {
        is InsertStatement -> println("inserting ${statement.row} ...")
        is SelectStatement -> println("selecting $statement ")
    }
}