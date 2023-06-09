import repl.command.execute as executeStatement
import repl.command.prepare as prepareStatement
import repl.metacommand.prepare as prepareMetaStatement
import repl.display.printPrompt
import repl.metacommand.isPossibleMetaStatement
import repl.metacommand.execute as executeMetaStatement

fun main(args: Array<String>) {
    while(true) {
        printPrompt()
        val input = readlnOrNull() ?: break

        if (isPossibleMetaStatement(input)) {
            with(prepareMetaStatement(input)) {
                when(this) {
                    null -> println("Unrecognized command '$input'")
                    else -> executeMetaStatement(this)
                }
            }
            continue
        }

        with(prepareStatement(input)) {
            when(this) {
                null -> println("Unrecognized command '$input'")
                else -> executeStatement(this)
            }
        }
    }
}