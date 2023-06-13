package will.of.d.sulsul.alcohol.drinkingLimit.dto.response

import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimit

data class DrinkingLimitRes(
    val userId: String,
    val sojuCount: Int,
    val beerCount: Int,
    val alcoholAmount: Double
) {
    companion object {
        fun of(drinkingLimit: DrinkingLimit): DrinkingLimitRes {
            return DrinkingLimitRes(
                userId = drinkingLimit.userId.toString(),
                sojuCount = drinkingLimit.sojuCount,
                beerCount = drinkingLimit.beerCount,
                alcoholAmount = drinkingLimit.alcoholAmount
            )
        }
    }
}
