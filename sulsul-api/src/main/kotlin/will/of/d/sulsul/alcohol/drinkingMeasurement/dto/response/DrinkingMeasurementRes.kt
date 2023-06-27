package will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.DrinkingMeasurement
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.Drinks
import java.time.LocalDateTime

class DrinkingMeasurementRes(
    @Schema(description = "식별 id", example = "6481c97405e8335a58bc4337")
    val id: String,
    @Schema(description = "유저가 총 마신 술의 잔", example = "4")
    val totalDrinkGlasses: Int,
    @Schema(description = "유저가 마신 술의 평균 알콜 도수", example = "16.9")
    val averageAlcoholContent: Double,
    @Schema(description = "유저가 술을 마신 시간", example = "3시간 20분")
    val drinkingDuration: String,
    @Schema(description = "유저가 마신 술의 칼로리", example = "399")
    val alcoholCalorie: Int,
    @Schema(description = "유저가 마신 날짜", example = "2021-08-20T15:00:00")
    val drankAt: LocalDateTime,
    @Schema(description = "유저가 마신 술의 종류와 잔 수", example = "[{\"drinkType\":\"소주\",\"glasses\":4}]")
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
