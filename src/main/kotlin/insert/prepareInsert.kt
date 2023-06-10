package insert

import statement.InsertStatement
import statement.Row
import utils.Either
import utils.Failure
import utils.Success
import utils.get

fun prepareInsert(input: String): Either<InsertStatement> {
    val tokenizedResult = tokenizeInput(input).let {
        if (it is Failure) {
            return Failure(it.error)
        } else {
            it.get()
        }
    }

    return Success(InsertStatement(
        table = tokenizedResult.tableName,
        row = Row(
            id = tokenizedResult.id,
            fields = tokenizedResult.fields
        )
    ))
}

private fun tokenizeInput(input: String): Either<TokenizedInput> {
    val tokens = input.split(" ").filter { it != "insert" }.reversed()
    val fields = tokens.filter { it.contains("=") }
    val fieldsAsMap = fields.associate { it.split("=")[0] to it.split("=")[1] }
    val id = fields.find { it.startsWith("id") }?.split("=")?.get(1)

    val tableName = parseTableName(tokens)
        ?: return Failure("insert statement requires table name")
    return Success(TokenizedInput(
        tableName = tableName,
        id = id as String,
        fields = fieldsAsMap,
    ))
}

private fun parseTableName(tokens: List<String>): String? = tokens.find { !it.contains("=") }

private data class TokenizedInput(
    val tableName: String,
    val id: String,
    val fields: Map<String, String>
)
