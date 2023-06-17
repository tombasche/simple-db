package repl.args

data class Args(
    val dbName: String,
    val query: String?
) {
    companion object {
        const val DEFAULT_DB_NAME = "simple.db"
    }
}
