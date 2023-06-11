import command.execute as executeStatement
import command.prepare as prepareStatement
import repl.metacommand.prepare as prepareMetaStatement
import repl.display.printPrompt
import repl.input.clean
import repl.metacommand.isPossibleMetaStatement
import storage.Table
import utils.Failure
import utils.Success
import repl.metacommand.execute as executeMetaStatement

fun main(args: Array<String>) {
    val table = Table.open("todo.db")
    while(true) {
        printPrompt()
        val input = readlnOrNull()?.let { clean(it) } ?: continue
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
                is Failure -> println("Failed to parse statement: '$input' due to ${this.error}")
                is Success -> println(executeStatement(table, this.value))
            }
        }
    }
}