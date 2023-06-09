import repl.MetaCommand
import repl.printPrompt
import repl.runMetaCommand

fun main(args: Array<String>) {
    while(true) {
        printPrompt()
        val input = readlnOrNull() ?: break
        if (input.startsWith("/")) {
            when (runMetaCommand(input)) {
                MetaCommand.SUCCESS -> println("Should do something here")
                MetaCommand.UNRECOGNIZED -> println("Unrecognized command '$input'")
            }
        }
    }
}