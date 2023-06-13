package repl.args

import utils.Either
import utils.Failure
import utils.Success
import utils.get

fun parseArgs(args: List<String>): Either<Args> {
    val dbName = when(val result = getDbNameValue(args)) {
        is Success -> appendSuffixIfRequired(result.get())
        is Failure -> return result
    }

    return Success(Args(
        dbName = dbName
    ))
}

private fun getDbNameValue(args: List<String>): Either<String> {
    val key = args.find { it == "--db-name" } ?: return Success(Args.DEFAULT_DB_NAME)
    val value = key.let { args.getOrNull(args.indexOf(it) + 1) }
        ?: return Failure("No value provided for --db-name")
    return Success(value)
}

private fun appendSuffixIfRequired(dbName: String): String =
    if (dbName.endsWith(".db")) dbName else "$dbName.db"
