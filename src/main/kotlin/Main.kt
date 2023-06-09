import repl.MetaCommand
import repl.execute
import repl.prepare
import repl.printPrompt
import repl.runMetaCommand

fun main(args: Array<String>) {
    while(true) {
        printPrompt()
        val input = readlnOrNull() ?: break

        // TODO refactor this into prepare or similar?
        if (input.startsWith("/")) {
            if (runMetaCommand(input) == MetaCommand.UNRECOGNIZED) {
                println("Unrecognized command '$input'")
            }
            continue
        }

        with(prepare(input)) {
            when(this) {
                null -> println("Unknown command $input")
                else -> execute(this)
            }
        }
    }
}