package repl.args

data class Args(
    val dbName: String,
) {
    companion object {
        const val DEFAULT_DB_NAME = "simple.db"
    }
}
