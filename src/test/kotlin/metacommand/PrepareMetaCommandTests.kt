package metacommand

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import repl.metacommand.MetaCommand
import repl.metacommand.prepare

class PrepareMetaCommandTests: FunSpec({
    test("if input starts with /exit, it's an exit command") {
        prepare("/exit") shouldBe MetaCommand.Exit
    }
    test("if input starts with /clear, it's a clear command") {
        prepare("/clear") shouldBe MetaCommand.Clear
    }
    test("if an unknown input is given, it returns null") {
        prepare("/whatever") shouldBe null
    }
})