package repl.args

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import utils.Failure
import utils.get

class ArgParsingTests: FunSpec({
    test("parses out the db name from input args") {
        val input = listOf("--db-name", "example.db")
        val result = parseArgs(input).get()
        result.dbName shouldBe "example.db"
    }

    test("if the extension is forgotten, then add the extension magically") {
        val input = listOf("--db-name", "example")
        val result = parseArgs(input).get()
        result.dbName shouldBe "example.db"
    }

    test("adding the arg requires the value") {
        val input = listOf("--db-name")
        val result = parseArgs(input)
        result.shouldBeTypeOf<Failure>()
        result.error shouldBe "No value provided for --db-name"
    }

    test("no db-name picks the default db name") {
        val input = emptyList<String>()
        val result = parseArgs(input).get()
        result.dbName shouldBe "simple.db"
    }
})