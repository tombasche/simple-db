package storage

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.maps.shouldBeEmpty
import io.kotest.matchers.shouldBe
import statement.Row
import java.io.File

class BlockStorageIntegrationTests: FunSpec({
    beforeSpec {
        deleteTestDb()
    }

    afterEach {
        deleteTestDb()
    }

    test("flushes the block storage to disk and reads it back") {
        val blockStorage = BlockStorage.open(localTestDbName())
        allocateRow(blockStorage, "users", Row(id="1", fields = mapOf(
            "username" to "cstack",
            "email" to "foo@bar.com"
        )))
        allocateRow(blockStorage, "users", Row(id="2", fields = mapOf(
            "username" to "foobar",
            "email" to "foo@bar2.com"
        )))

        blockStorage.flush()
        blockStorage.file.readLines() shouldBe listOf(
            "users:1,username=cstack,email=foo@bar.com",
            "users:2,username=foobar,email=foo@bar2.com",
        )
        blockStorage.rows.shouldBeEmpty()

        val secondRun = BlockStorage.open(localTestDbName())

        secondRun.rows shouldBe mapOf(
            "users" to mutableListOf(
                "1,username=cstack,email=foo@bar.com".toByteArray(),
                "2,username=foobar,email=foo@bar2.com".toByteArray(),
            )
        )
    }
})

private fun localTestDbName() = "src/test/kotlin/storage/test.db"
private fun deleteTestDb() {
    if (File(localTestDbName()).exists()) {
        File(localTestDbName()).delete()
    }
}