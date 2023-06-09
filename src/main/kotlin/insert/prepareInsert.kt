package insert

import statement.InsertStatement
import statement.TempRow

// TODO - could this use a monadic type?
fun prepareInsert(input: String): InsertStatement {

    val tokenized = input.split(" ").filter { it != "insert"}

    return InsertStatement(
        row = TempRow(
            id = tokenized[0],
            username = tokenized[1],
            email = tokenized[2]
        )
    )
}