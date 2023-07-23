package will.of.d.sulsul.alcohol.drinkingMeasurement.service

import org.bson.types.ObjectId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.Drink
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.DrinkingMeasurement
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.Drinks
import will.of.d.sulsul.alcohol.drinkingMeasurement.repository.DrinkingMeasurementRepository
import will.of.d.sulsul.alcohol.drinkingMeasurement.vo.DrinkingMeasurementVO
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
                Drink.SOJU.drinkType -> {
                    averageAlcoholContent += Drink.SOJU.alcoholContent
                    totalCalorie += it.glasses * Drink.SOJU.calorie
                }
                Drink.BEER.drinkType -> {
                    averageAlcoholContent += Drink.BEER.alcoholContent
                    totalCalorie += it.glasses * Drink.BEER.calorie
                }
                Drink.WINE.drinkType -> {
                    averageAlcoholContent += Drink.WINE.alcoholContent
                    totalCalorie += it.glasses * Drink.WINE.calorie
                }
                Drink.WHISKY.drinkType -> {
                    averageAlcoholContent += Drink.WHISKY.alcoholContent
                    totalCalorie += it.glasses * Drink.WHISKY.calorie
                }
                Drink.KAOLIANG.drinkType -> {
                    averageAlcoholContent += Drink.KAOLIANG.alcoholContent
                    totalCalorie += it.glasses * Drink.KAOLIANG.calorie
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
