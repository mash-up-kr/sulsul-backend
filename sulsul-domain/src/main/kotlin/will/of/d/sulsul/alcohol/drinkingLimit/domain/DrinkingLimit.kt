package will.of.d.sulsul.alcohol.drinkingLimit.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "drinking_limit")
data class DrinkingLimit(
    @Id
    val id: ObjectId? = null,
    val kakaoUserId: Long,
    val drinkType: String,
    val drinkBottle: Int,
    val alcoholAmount: Double = 0.0
)
