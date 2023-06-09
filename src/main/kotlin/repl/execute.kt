package repl

fun execute(command: Command) {
    when(command) {
        Command.Insert -> println("inserting ... ")
        Command.Select -> println("selecting ... ")
    }
}