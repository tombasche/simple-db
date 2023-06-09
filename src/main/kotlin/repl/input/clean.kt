package repl.input

fun clean(rawInput: String): String? {
    if (rawInput.isBlank()) return null
    return rawInput.lowercase().trim()
}