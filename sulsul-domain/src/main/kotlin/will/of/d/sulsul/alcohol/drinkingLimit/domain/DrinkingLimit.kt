package will.of.d.sulsul.alcohol.drinkingLimit.domain

import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.Min
import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import will.of.d.sulsul.alcohol.Drink
import will.of.d.sulsul.alcohol.drinkingLimit.TitleOfDrinkingLimit
import will.of.d.sulsul.common.findBy
import java.time.LocalDateTime

@Document(collection = "drinking_limit")
data class DrinkingLimit(
    @Id
    val id: ObjectId? = null,
    val kakaoUserId: Long,
    val drinkType: String,

    @field: Min(0)
    val glass: Int,
    val alcoholAmount: Double = 0.0,

    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    val updatedDate: LocalDateTime = LocalDateTime.now()
) {
    @AssertTrue
    fun isValidDrinkType(): Boolean {
        return Drink::drinkType findBy drinkType != null
    }

    companion object {
        fun from(kakaoUserId: Long, drinkType: String, drinkBottle: Int, alcoholAmount: Double = 0.0): DrinkingLimit {
            return DrinkingLimit(
                kakaoUserId = kakaoUserId,
                drinkType = drinkType,
                glass = drinkBottle,
                alcoholAmount = alcoholAmount
            )
        }
    }

    fun createTitle(): TitleOfDrinkingLimit {
        return when (this.glass) {
            in 0..7 -> TitleOfDrinkingLimit.BRONZE
            in 8..15 -> TitleOfDrinkingLimit.SILVER
            in 16..23 -> TitleOfDrinkingLimit.GOLD
            in 24..31 -> TitleOfDrinkingLimit.PLATINUM
            in 32..40 -> TitleOfDrinkingLimit.DIAMOND
            else -> TitleOfDrinkingLimit.MASTER
        }
    }
}
