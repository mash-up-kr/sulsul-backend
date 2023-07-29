package will.of.d.sulsul.alcohol.drinkingMeasurement.service

import org.bson.types.ObjectId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.DrinkingMeasurement
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.Drinks
import will.of.d.sulsul.alcohol.drinkingMeasurement.repository.DrinkingMeasurementRepository
import will.of.d.sulsul.alcohol.drinkingMeasurement.vo.DrinkingMeasurementVO
import will.of.d.sulsul.drink.domain.Drink
import java.time.Duration

@Service
class DrinkingMeasurementService(
    private val drinkingMeasurementRepository: DrinkingMeasurementRepository
) {
    fun save(drinkingMeasurement: DrinkingMeasurement): DrinkingMeasurement {
        return drinkingMeasurementRepository.save(drinkingMeasurement)
    }

    fun findById(id: String): DrinkingMeasurement? {
        return drinkingMeasurementRepository.findByIdOrNull(ObjectId(id))
    }

    fun measurement(drinkingMeasurementVO: DrinkingMeasurementVO):
        DrinkingMeasurement {
        val (userId, drinks, drinkingStartTime, drinkingEndTime, totalDrinkGlasses) = drinkingMeasurementVO
        val duration = Duration.between(drinkingStartTime, drinkingEndTime)
        val hours = duration.toHours()
        val minutes = duration.toMinutes() % 60
        val drinkingDuration = "${hours}시간 ${minutes}분"
        var averageAlcoholContent = 0.0
        var totalCalorie = 0

        drinks.forEach() {
            when (it.drinkType) {
                Drink.SOJU.type -> {
                    averageAlcoholContent += Drink.SOJU.alcoholPercentage
                    totalCalorie += it.glasses * Drink.SOJU.caloriePerGlass
                }
                Drink.BEER.type -> {
                    averageAlcoholContent += Drink.BEER.alcoholPercentage
                    totalCalorie += it.glasses * Drink.BEER.caloriePerGlass
                }
                Drink.WINE.type -> {
                    averageAlcoholContent += Drink.WINE.alcoholPercentage
                    totalCalorie += it.glasses * Drink.WINE.caloriePerGlass
                }
                Drink.WHISKY.type -> {
                    averageAlcoholContent += Drink.WHISKY.alcoholPercentage
                    totalCalorie += it.glasses * Drink.WHISKY.caloriePerGlass
                }
                Drink.KAOLIANG.type -> {
                    averageAlcoholContent += Drink.KAOLIANG.alcoholPercentage
                    totalCalorie += it.glasses * Drink.KAOLIANG.caloriePerGlass
                }
            }
        }
        averageAlcoholContent /= drinks.size

        val document = DrinkingMeasurement.from(userId, drinkingDuration, totalCalorie, averageAlcoholContent, totalDrinkGlasses, drinks.map { Drinks.from(it.drinkType, it.glasses) }, drinkingStartTime)

        return drinkingMeasurementRepository.save(document)
    }

    fun findAllByUserId(userId: Long): List<DrinkingMeasurement> {
        return drinkingMeasurementRepository.findAllByUserId(userId)
    }
}
