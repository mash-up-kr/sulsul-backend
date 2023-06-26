package will.of.d.sulsul.alcohol.drinkingMeasurement.vo

import java.time.LocalDateTime

data class DrinkingMeasurementVO(
    val userId: Long,
    val drinks: List<DrinkingAmountVO>,
    val drinkingStartTime: LocalDateTime,
    val drinkingEndTime: LocalDateTime,
    val totalDrinkGlasses: Int
) {
    companion object {
        fun from(
            userId: Long,
            drinks: List<DrinkingAmountVO>,
            drinkingStartTime: LocalDateTime,
            drinkingEndTime: LocalDateTime,
            totalDrinkGlasses: Int
        ): DrinkingMeasurementVO {
            return DrinkingMeasurementVO(
                userId = userId,
                drinks = drinks,
                drinkingStartTime = drinkingStartTime,
                drinkingEndTime = drinkingEndTime,
                totalDrinkGlasses = totalDrinkGlasses
            )
        }
    }
}

class DrinkingAmountVO(
    val drinkType: String,
    val glasses: Int
)
