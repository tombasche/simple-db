package repl

import kotlin.system.exitProcess

enum class MetaCommand {
    SUCCESS,
    UNRECOGNIZED
}

fun runMetaCommand(input: String): MetaCommand =
    when (input) {
        "/exit" -> exitProcess(0)
        else -> MetaCommand.UNRECOGNIZED
    }
