package insert

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import statement.InsertStatement
import utils.Failure
import utils.Success

class PrepareInsertTests: FunSpec({
    test("args are parsed from the input") {
        val input = "insert id=1 name=cstack email=foo@bar.com users"
        val result = (prepareInsert(input) as Success).value
        result.table shouldBe "users"
        result.row.id shouldBe "1"
        result.row.fields["name"] shouldBe "cstack"
        result.row.fields["email"] shouldBe "foo@bar.com"
    }

    test("insert statement requires table name") {
        val input = "insert id=1 name=cstack email=foo@bar.com"

        val result = prepareInsert(input)
        result shouldBe Failure("insert statement requires table name")
    }
})