package storage

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.maps.shouldBeEmpty
import io.kotest.matchers.shouldBe
import statement.Row
import java.io.File

class StorageIntegrationTests : FunSpec({
    beforeSpec {
        deleteTestDb()
    }

    afterEach {
        deleteTestDb()
    }

    test("flushes the storage to disk and reads it back") {
        val storage = Storage.open(localTestDbName())
        allocateRow(
            storage, "users", Row(
                id = "1", fields = mapOf(
                    "username" to "cstack",
                    "email" to "foo@bar.com"
                )
            )
        )
        allocateRow(
            storage, "users", Row(
                id = "2", fields = mapOf(
                    "username" to "foobar",
                    "email" to "foo@bar2.com"
                )
            )
        )

        storage.flush()
        storage.file.readLines() shouldBe listOf(
            "users:1,username=cstack,email=foo@bar.com",
            "users:2,username=foobar,email=foo@bar2.com",
        )
        storage.rows.shouldBeEmpty()

        val secondRun = Storage.open(localTestDbName())

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