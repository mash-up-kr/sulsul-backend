package will.of.d.sulsul.drink.service

import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.drinkingLimit.vo.DrinkingLimitVO
import will.of.d.sulsul.alcohol.drinkingMeasurement.service.DrinkingMeasurementInfo
import will.of.d.sulsul.alcohol.drinkingMeasurement.vo.DrinkingAmountVO
import will.of.d.sulsul.common.findBy
import will.of.d.sulsul.drink.domain.Drink
import will.of.d.sulsul.drink.domain.DrinkCard

@Service
class AlcoholService {
    fun calculateDrinkingLimits(alcohol: Int): List<DrinkingLimitVO> {
        return Drink.values().map { drink ->
            DrinkingLimitVO(
                drink = drink,
                glass = alcohol.takeIf { alcohol -> alcohol > 0 }
                    ?.let { alcohol -> (alcohol / drink.alcoholAmountPerGlass).takeIf { it > 0 } ?: 1 }
                    ?: 0
            )
        }
    }

    fun calculateAlcohol(drinkingLimitVO: DrinkingLimitVO): Int {
        return drinkingLimitVO.drink.alcoholAmountPerGlass * drinkingLimitVO.glass
    }

    fun calculateDrinkingMeasurementInfo(drinks: List<DrinkingAmountVO>): DrinkingMeasurementInfo {
        var averageAlcoholPercent = 0.0
        var totalCalorie = 0
        var totalAlcoholAmount = 0

        var totalGlasses = 0

        drinks.forEach() {
            val drink = (Drink::type findBy it.drinkType)!!

            totalAlcoholAmount += drink.alcoholAmountPerGlass * it.glasses
            totalCalorie += drink.caloriePerGlass * it.glasses
            averageAlcoholPercent += drink.alcoholPercentage * it.glasses

            totalGlasses += it.glasses
        }

        averageAlcoholPercent /= totalGlasses

        return DrinkingMeasurementInfo(
            averageAlcoholPercent = averageAlcoholPercent,
            totalCalorie = totalCalorie,
            totalAlcoholAmount = totalAlcoholAmount
        )
    }

    fun defineDrinkCardImageUrl(drinks: List<DrinkingAmountVO>): String {
        var maxGlass = 0
        var drinkType = Drink.SOJU.type

        drinks.forEach() {
            if (maxGlass > it.glasses) {
                maxGlass = it.glasses
                drinkType = it.drinkType
            }
        }

        return (DrinkCard::type findBy drinkType)!!.drinkCardImageUrl
    }
}
