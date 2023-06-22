package will.of.d.sulsul.alcohol.drinkingLimit.dto.response

import will.of.d.sulsul.alcohol.Drink
import will.of.d.sulsul.alcohol.drinkingLimit.TitleOfDrinkingLimit
import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimit
import will.of.d.sulsul.common.findBy

data class DrinkingLimitRes(
    val titleOfDrinkingLimit: TitleOfDrinkingLimit,
    val drink: Drink,
    val alcoholAmount: Double
) {
    companion object {
        fun of(drinkingLimit: DrinkingLimit): DrinkingLimitRes {
            val titleEnum: TitleOfDrinkingLimit = drinkingLimit.createTitle()
            val drinkEnum: Drink? = Drink::drinkType findBy drinkingLimit.drinkType

            return DrinkingLimitRes(
                titleOfDrinkingLimit = titleEnum,
                drink = drinkEnum!!,
                alcoholAmount = drinkingLimit.alcoholAmount
            )
        }
    }
}
