import repl.display.printPrompt
import repl.input.clean
import repl.metacommand.isPossibleMetaStatement
import storage.Table
import storage.flush
import sun.misc.Signal
import utils.Failure
import utils.Success
import command.execute as executeStatement
import command.prepare as prepareStatement
import repl.metacommand.execute as executeMetaStatement
import repl.metacommand.prepare as prepareMetaStatement

fun main(args: Array<String>) {
    // TODO allow passing in a db name from args
    val table = Table.open("todo.db")
    registerShutdownHandler(table)

    while (true) {
        printPrompt()
        // TODO allow passing in the input from args
        val input = readlnOrNull()?.let { clean(it) } ?: continue
        if (isPossibleMetaStatement(input)) {
            with(prepareMetaStatement(input)) {
                when (this) {
                    null -> println("Unrecognized command '$input'")
                    else -> {
                        table.storage.flush()
                        executeMetaStatement(this, table)
                    }
                }
            }
            continue
        }

        with(prepareStatement(input)) {
            when (this) {
                null -> println("Unrecognized command '$input'")
                is Failure -> println("Failed to parse statement: '$input' due to ${this.error}")
                is Success -> println(executeStatement(table, this.value))
            }
        }
    }
}

private fun registerShutdownHandler(table: Table) {
    Signal.handle(Signal("INT")) {
        table.storage.flush()
    }
    Signal.handle(Signal("TERM")) {
        table.storage.flush()
    }
}
