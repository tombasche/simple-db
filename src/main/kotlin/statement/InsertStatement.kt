package statement

data class InsertStatement(
    val collectionName: String,
    val row: Row
) : Statement()


typealias FieldName = String
typealias Id = String
data class Row(
    val id: Id,
    val fields: Map<FieldName, *>
)
