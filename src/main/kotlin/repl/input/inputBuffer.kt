package repl.input

fun inputBuffer(input: String): List<String> =
    input.split(";").mapNotNull(::clean)
