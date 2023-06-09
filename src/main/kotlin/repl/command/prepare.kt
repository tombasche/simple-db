package repl.command

fun prepare(input: String): Command? {
    if (input.startsWith("insert")) return Command.Insert
    if (input.startsWith("select")) return Command.Select
    return null
}
