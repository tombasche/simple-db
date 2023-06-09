package repl.metacommand

import kotlin.system.exitProcess

fun execute(metaCommand: MetaCommand): Unit =
    when (metaCommand) {
        MetaCommand.Exit -> exitProcess(0)
    }
