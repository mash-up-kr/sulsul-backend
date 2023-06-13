package will.of.d.sulsul.alcohol.drinkingLimit.dto.response

import will.of.d.sulsul.alcohol.Drink
import will.of.d.sulsul.alcohol.drinkingLimit.TitleOfDrinkingLimit
import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimit

data class DrinkingLimitRes(
    val titleOfDrinkingLimit: TitleOfDrinkingLimit,
    val drink: Drink,
    val alcoholAmount: Double
) {
    companion object {
        fun of(drinkingLimit: DrinkingLimit): DrinkingLimitRes {
            val title: TitleOfDrinkingLimit = when (drinkingLimit.drinkBottle) {
                in 0..7 -> TitleOfDrinkingLimit.BRONZE
                in 8..15 -> TitleOfDrinkingLimit.SILVER
                in 16..23 -> TitleOfDrinkingLimit.GOLD
                in 24..31 -> TitleOfDrinkingLimit.PLATINUM
                in 32..40 -> TitleOfDrinkingLimit.DIAMOND
                else -> TitleOfDrinkingLimit.MASTER
            }

            return when (drinkingLimit.drinkType) {
                Drink.SOJU.name -> DrinkingLimitRes(titleOfDrinkingLimit = title, drink = Drink.SOJU, alcoholAmount = drinkingLimit.alcoholAmount)
                Drink.WINE.name -> DrinkingLimitRes(titleOfDrinkingLimit = title, drink = Drink.WINE, alcoholAmount = drinkingLimit.alcoholAmount)
                Drink.BEER.name -> DrinkingLimitRes(titleOfDrinkingLimit = title, drink = Drink.BEER, alcoholAmount = drinkingLimit.alcoholAmount)
                Drink.WHISKY.name -> DrinkingLimitRes(titleOfDrinkingLimit = title, drink = Drink.WHISKY, alcoholAmount = drinkingLimit.alcoholAmount)
                else -> DrinkingLimitRes(titleOfDrinkingLimit = title, drink = Drink.KAOLIANG, alcoholAmount = drinkingLimit.alcoholAmount)
            }
        }
    }
}
