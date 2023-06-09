package repl

fun prepare(input: String): Command? {
    // TODO lift the casing out somehow and give a stricter contract?
    if (input.lowercase().startsWith("insert")) return Command.Insert
    if (input.lowercase().startsWith("select")) return Command.Select
    return null
}