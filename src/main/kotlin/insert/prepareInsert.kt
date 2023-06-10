package insert

import statement.InsertStatement
import statement.Row

// TODO - could this use a monadic type?
fun prepareInsert(input: String): InsertStatement {

    val tokenized = input.split(" ").filter { it != "insert"}

    return InsertStatement(
        row = Row(
            id = tokenized[0],
            fields = emptyMap<String, String>()
        )
    )
}