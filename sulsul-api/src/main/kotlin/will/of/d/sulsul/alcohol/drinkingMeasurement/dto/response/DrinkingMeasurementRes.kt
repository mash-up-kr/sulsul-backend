package will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response

import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.DrinkingMeasurement
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.Drinks
import java.time.LocalDateTime

class DrinkingMeasurementRes(
    val id: String,
    val totalDrinkGlasses: Int,
    val averageAlcoholContent: Double,
    val drinkingDuration: String,
    val alcoholCalorie: Int,
    val drankAt: LocalDateTime,
    val drinks: List<Drinks>
) {
    companion object {
        fun of(drinkingMeasurement: DrinkingMeasurement): DrinkingMeasurementRes {
            return DrinkingMeasurementRes(
                id = drinkingMeasurement.id.toString(),
                totalDrinkGlasses = drinkingMeasurement.totalDrinkGlasses,
                averageAlcoholContent = drinkingMeasurement.averageAlcoholContent,
                drinkingDuration = drinkingMeasurement.drinkingDuration,
                alcoholCalorie = drinkingMeasurement.alcoholCalorie,
                drankAt = drinkingMeasurement.drankAt,
                drinks = drinkingMeasurement.drinks
            )
        }
    }
}
