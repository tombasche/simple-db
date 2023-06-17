import repl.args.parseArgs
import repl.display.printPrompt
import repl.input.clean
import repl.metacommand.isPossibleMetaStatement
import storage.Table
import storage.flush
import sun.misc.Signal
import utils.Failure
import utils.Success
import kotlin.system.exitProcess
import command.execute as executeStatement
import command.prepare as prepareStatement
import repl.metacommand.execute as executeMetaStatement
import repl.metacommand.prepare as prepareMetaStatement

fun main(args: Array<String>) {
    val replArgs = when (val argsResult = parseArgs(args.toList())) {
        is Failure -> {
            println(argsResult.error)
            return
        }

        is Success -> {
            argsResult.value
        }
    }
    val table = Table.open(replArgs.dbName)
    registerShutdownHandler(table)
    var initialInput: String? = replArgs.query
    while (true) {
        printPrompt()
        val input = initialInput ?: readlnOrNull()?.let { clean(it) } ?: continue
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
        initialInput = null
    }
}

private fun registerShutdownHandler(table: Table) {
    Signal.handle(Signal("INT")) {
        table.storage.flush()
        exitProcess(0)
    }
    Signal.handle(Signal("TERM")) {
        table.storage.flush()
        exitProcess(0)
    }
}
