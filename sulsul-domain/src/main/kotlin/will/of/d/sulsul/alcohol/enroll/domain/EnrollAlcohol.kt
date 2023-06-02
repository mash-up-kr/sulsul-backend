package will.of.d.sulsul.alcohol.enroll.domain

import nonapi.io.github.classgraph.json.Id
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "enroll_alcohol")
class EnrollAlcohol(
    @Id
    val id: ObjectId? = null,
    val userId: ObjectId,
    val sojuCount: Int,
    val beerCount: Int,
    val alcoholAmount: Int

    // TODO : 주량 정확히 정해지면 각 주종별 개수 담아두는 필드 추가하기
)
