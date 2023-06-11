package storage

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import statement.Row

class PagerTests: FunSpec({
    test("a new page is created once the current one is filled") {
        val pager = Pager.open("test.db")
        repeat(1000) {
            allocateRow(pager, "users", Row(id = it.toString(), fields = emptyMap<String, String>()))
        }
        pager.pages.size shouldBe 10
    }
})
