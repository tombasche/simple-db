package repl.metacommand

fun prepare(input: String): MetaCommand? {
    return if (input.lowercase().startsWith("/exit")) {
         MetaCommand.Exit
    } else null
}

fun isPossibleMetaStatement(input: String) = input.startsWith("/")