package will.of.d.sulsul.alcohol.enroll.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "enroll_alcohol")
data class EnrollAlcohol(
    @Id
    val id: ObjectId? = null,
    val userId: ObjectId,
    val sojuCount: Int,
    val beerCount: Int,
    val alcoholAmount: Double = 0.0,

    // TODO : 주량 정확히 정해지면 각 주종별 개수 담아두는 필드 추가하기

    @CreatedDate var createdDate: LocalDateTime? = null,
    @LastModifiedDate var updatedDate: LocalDateTime? = null
)
