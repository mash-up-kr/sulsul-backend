package will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response

import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.DrinkingMeasurementDTO
import java.time.LocalDateTime

class DrinkingMeasurementRes(
    val totalDrinkGlasses: Int,
    val averageAlcoholContent: Double,
    val drinkingDuration: String,
    val alcoholCalorie: Int,
    val drankAt: LocalDateTime,
    val drinks: List<DrinkingMeasurementDTO>
) {
    companion object {
    }
}
