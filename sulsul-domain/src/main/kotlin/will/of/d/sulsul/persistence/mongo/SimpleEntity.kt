package will.of.d.sulsul.persistence.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


// TODO: 기능 개발 시작 후 지우기
@Document(collection = "test")
data class SimpleEntity(
    @Id
    val id: ObjectId? = null,
    val value: String,
)
