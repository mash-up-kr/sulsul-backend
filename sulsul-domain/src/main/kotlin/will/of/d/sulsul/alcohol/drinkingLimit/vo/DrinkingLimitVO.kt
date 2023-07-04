package will.of.d.sulsul.alcohol.drinkingLimit.vo

import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.Min
import will.of.d.sulsul.common.findBy
import will.of.d.sulsul.drink.domain.Drink

data class DrinkingLimitVO(
    val kakaoUserId: Long,
    val drinkType: String,
    @field: Min(0)
    val glass: Int
) {

    @AssertTrue
    fun isValidDrinkType(): Boolean {
        return Drink::type findBy drinkType != null
    }

    companion object {
        fun from(kakaoUserId: Long, drinkType: String, glass: Int): DrinkingLimitVO {
            return DrinkingLimitVO(
                kakaoUserId = kakaoUserId,
                drinkType = drinkType,
                glass = glass
            )
        }
    }
}
