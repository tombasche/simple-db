package storage

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.maps.shouldBeEmpty
import io.kotest.matchers.shouldBe
import statement.Row
import java.io.File

class BlockStorageIntegrationTests: FunSpec({

    afterEach {
        if (File(localTestDbName()).exists()) {
            File(localTestDbName()).delete()
        }
    }

    test("flushes the block storage to disk and reads it back") {
        val blockStorage = BlockStorage.open(localTestDbName())
        allocateRow(blockStorage, "users", Row(id="1", fields = mapOf(
            "username" to "cstack",
            "email" to "foo@bar.com"
        )))

        blockStorage.flush()
        blockStorage.file.readLines() shouldBe listOf(
            "users:1,username=cstack,email=foo@bar.com"
        )
        blockStorage.rows.shouldBeEmpty()

    }
})

private fun localTestDbName() = "src/test/kotlin/storage/test.db"