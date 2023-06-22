package will.of.d.sulsul.alcohol.drinkingLimit.domain

import jakarta.validation.constraints.Min
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import will.of.d.sulsul.alcohol.Drink
import will.of.d.sulsul.common.findBy
import will.of.d.sulsul.exception.InvalidRequestException

@Document(collection = "drinking_limit")
data class DrinkingLimit(
    @Id
    val id: ObjectId? = null,
    val kakaoUserId: Long,
    val drinkType: String,

    @Min(0)
    val drinkBottle: Int,
    val alcoholAmount: Double = 0.0
) {
    companion object {
        fun from(kakaoUserId: Long, drinkType: String, drinkBottle: Int, alcoholAmount: Double = 0.0): DrinkingLimit {
            if (Drink::drinkType findBy drinkType == null) {
                throw InvalidRequestException("유효하지 않은 drink type 입니다 (valid : 소주, 와인, 맥주, 위스키, 고량주)")
            }

            return DrinkingLimit(
                kakaoUserId = kakaoUserId,
                drinkType = drinkType,
                drinkBottle = drinkBottle,
                alcoholAmount = alcoholAmount
            )
        }
    }
}
