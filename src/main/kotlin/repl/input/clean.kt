package repl.input

fun clean(rawInput: String): String? =
    if (rawInput.isBlank()) {
        null
    } else {
        rawInput.lowercase().trim()
    }
