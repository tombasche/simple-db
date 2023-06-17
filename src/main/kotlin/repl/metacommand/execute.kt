package repl.metacommand

import storage.Table
import storage.clear
import kotlin.system.exitProcess

fun execute(metaCommand: MetaCommand, table: Table): Unit =
    when (metaCommand) {
        MetaCommand.Exit -> {
            println("Bye!")
            exitProcess(0)
        }

        MetaCommand.Clear -> {
            table.storage.clear()
            println("Database ${table.storage.file.name} deleted.")
        }
    }
