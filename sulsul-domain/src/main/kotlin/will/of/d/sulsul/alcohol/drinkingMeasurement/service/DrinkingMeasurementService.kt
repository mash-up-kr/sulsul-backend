package will.of.d.sulsul.alcohol.drinkingMeasurement.service

import org.bson.types.ObjectId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.drinkingLimit.service.DrinkingLimitService
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.DrinkingMeasurement
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.Drinks
import will.of.d.sulsul.alcohol.drinkingMeasurement.repository.DrinkingMeasurementRepository
import will.of.d.sulsul.alcohol.drinkingMeasurement.vo.DrinkingMeasurementVO
import will.of.d.sulsul.drink.domain.Drink
import java.time.Duration

@Service
class DrinkingMeasurementService(
    private val drinkingMeasurementRepository: DrinkingMeasurementRepository,
    private val drinkingLimitService: DrinkingLimitService
) {
    fun save(drinkingMeasurement: DrinkingMeasurement): DrinkingMeasurement {
        return drinkingMeasurementRepository.save(drinkingMeasurement)
    }

    fun findById(id: String): DrinkingMeasurement? {
        return drinkingMeasurementRepository.findByIdOrNull(ObjectId(id))
    }

    fun measurement(drinkingMeasurementVO: DrinkingMeasurementVO): DrinkingMeasurement {
        val (userId, drinks, drinkingStartTime, drinkingEndTime, totalDrinkGlasses) = drinkingMeasurementVO
        val duration = Duration.between(drinkingStartTime, drinkingEndTime)
        val hours = duration.toHours()
        val minutes = duration.toMinutes() % 60
        val drinkingDuration = "${hours}시간 ${minutes}분"
        var averageAlcoholPercent = 0.0
        var totalCalorie = 0
        var totalAlcoholAmount = 0

        drinks.forEach() {
            when (it.drinkType) {
                Drink.SOJU.type -> {
                    averageAlcoholPercent += (Drink.SOJU.alcoholPercentage * it.glasses)
                    totalCalorie += it.glasses * Drink.SOJU.caloriePerGlass
                    totalAlcoholAmount += it.glasses * Drink.SOJU.alcoholAmountPerGlass
                }
                Drink.BEER.type -> {
                    averageAlcoholPercent += (Drink.BEER.alcoholPercentage * it.glasses)
                    totalCalorie += it.glasses * Drink.BEER.caloriePerGlass
                    totalAlcoholAmount += it.glasses * Drink.BEER.alcoholAmountPerGlass
                }
                Drink.WINE.type -> {
                    averageAlcoholPercent += (Drink.WINE.alcoholPercentage * it.glasses)
                    totalCalorie += it.glasses * Drink.WINE.caloriePerGlass
                    totalAlcoholAmount += it.glasses * Drink.WINE.alcoholAmountPerGlass
                }
                Drink.WHISKY.type -> {
                    averageAlcoholPercent += (Drink.WHISKY.alcoholPercentage * it.glasses)
                    totalCalorie += it.glasses * Drink.WHISKY.caloriePerGlass
                    totalAlcoholAmount += it.glasses * Drink.WHISKY.alcoholAmountPerGlass
                }
                Drink.KAOLIANG.type -> {
                    averageAlcoholPercent += (Drink.KAOLIANG.alcoholPercentage * it.glasses)
                    totalCalorie += it.glasses * Drink.KAOLIANG.caloriePerGlass
                    totalAlcoholAmount += it.glasses * Drink.KAOLIANG.alcoholAmountPerGlass
                }
            }
        }

        averageAlcoholPercent /= totalDrinkGlasses

        val extraGlasses = calculateExtraGlasses(
            myAlcoholAmount = drinkingLimitService.getAlcoholAmount(kakaoUserId = userId),
            todayAlcoholAmout = totalAlcoholAmount
        )

        val document = DrinkingMeasurement.from(
            userId = userId,
            drinkingDuration = drinkingDuration,
            alcoholCalorie = totalCalorie,
            alcoholAmount = totalAlcoholAmount,
            extraGlasses = extraGlasses,
            averageAlcoholContent = averageAlcoholPercent,
            totalDrinkGlasses = totalDrinkGlasses,
            drinks = drinks.map { Drinks.from(it.drinkType, it.glasses) },
            drankAt = drinkingStartTime
        )

        return drinkingMeasurementRepository.save(document)
    }

    fun findAllByUserId(userId: Long): List<DrinkingMeasurement> {
        return drinkingMeasurementRepository.findAllByUserId(userId)
    }

    fun calculateExtraGlasses(myAlcoholAmount: Int, todayAlcoholAmout: Int): Int {
        val diffAlcoholAmount = todayAlcoholAmout - myAlcoholAmount
        if (diffAlcoholAmount < 0) return 0

        // 소주 기준으로 카운팅
        return diffAlcoholAmount / Drink.SOJU.alcoholAmountPerGlass
    }
}
