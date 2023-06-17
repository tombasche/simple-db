import repl.args.parseArgs
import repl.display.printPrompt
import repl.input.clean
import repl.input.inputBuffer
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
    val initialInput = replArgs.query?.let { inputBuffer(it).toMutableList() } ?: mutableListOf()
    while (true) {
        printPrompt()
        val inputs = if (initialInput.isNotEmpty()) {
            initialInput
        } else {
            readlnOrNull()?.let { inputBuffer(it) } ?: continue
        }

        inputs.forEachIndexed { idx, input ->
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
                inputs.drop(idx)
            }
        }
        inputs.forEachIndexed {
            idx, input ->
            with(prepareStatement(input)) {
                when (this) {
                    null -> println("Unrecognized command '$input'")
                    is Failure -> println("Failed to parse statement: '$input' due to ${this.error}")
                    is Success -> println(executeStatement(table, this.value))
                }
            }
            inputs.drop(idx)
        }
        initialInput.clear()
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
