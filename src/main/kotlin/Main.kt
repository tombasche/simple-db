import command.execute as executeStatement
import command.prepare as prepareStatement
import repl.metacommand.prepare as prepareMetaStatement
import repl.display.printPrompt
import repl.input.clean
import repl.metacommand.isPossibleMetaStatement
import storage.Table
import storage.flush
import sun.misc.Signal
import sun.misc.SignalHandler
import utils.Failure
import utils.Success
import kotlin.system.exitProcess
import repl.metacommand.execute as executeMetaStatement

fun main(args: Array<String>) {

    val table = Table.open("todo.db")
    registerShutdownHandler(table)

    while(true) {
        printPrompt()
        val input = readlnOrNull()?.let { clean(it) } ?: continue
        if (isPossibleMetaStatement(input)) {
            with(prepareMetaStatement(input)) {
                when(this) {
                    null -> println("Unrecognized command '$input'")
                    else -> {
                        table.blockStorage.flush()
                        executeMetaStatement(this)
                    }
                }
            }
            continue
        }

        with(prepareStatement(input)) {
            when(this) {
                null -> println("Unrecognized command '$input'")
                is Failure -> println("Failed to parse statement: '$input' due to ${this.error}")
                is Success -> println(executeStatement(table, this.value))
            }
        }
    }
}

private fun registerShutdownHandler(table: Table) {
    Signal.handle(Signal("INT")) {
        table.blockStorage.flush()
    }
    Signal.handle(Signal("TERM")) {
        table.blockStorage.flush()
    }
}