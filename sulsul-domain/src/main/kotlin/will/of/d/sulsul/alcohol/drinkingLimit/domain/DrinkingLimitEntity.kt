package will.of.d.sulsul.alcohol.drinkingLimit.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import will.of.d.sulsul.alcohol.drinkingLimit.TitleOfDrinkingLimit
import will.of.d.sulsul.alcohol.drinkingLimit.vo.DrinkingLimitVO
import will.of.d.sulsul.common.findBy
import will.of.d.sulsul.drink.domain.Drink
import java.time.LocalDateTime

@Document(collection = "drinking_limit")
data class DrinkingLimitEntity(
    @Id
    val id: ObjectId? = null,
    val kakaoUserId: Long,
    val drinkType: String,
    val glass: Int,
    val alcoholAmount: Int,

    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    val updatedDate: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun from(drinkingLimitVO: DrinkingLimitVO): DrinkingLimitEntity {
            val drink: Drink? = Drink::type findBy drinkingLimitVO.drinkType

            return DrinkingLimitEntity(
                kakaoUserId = drinkingLimitVO.kakaoUserId,
                drinkType = drinkingLimitVO.drinkType,
                glass = drinkingLimitVO.glass,
                alcoholAmount = drink!!.alcoholAmountPerGlass * drinkingLimitVO.glass
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
