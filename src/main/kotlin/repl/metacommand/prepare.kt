package repl.metacommand

fun prepare(input: String): MetaCommand? =
    when(input.lowercase()) {
        "/exit" -> MetaCommand.Exit
        "/clear" -> MetaCommand.Clear
        else -> null
    }


fun isPossibleMetaStatement(input: String) = input.startsWith("/")