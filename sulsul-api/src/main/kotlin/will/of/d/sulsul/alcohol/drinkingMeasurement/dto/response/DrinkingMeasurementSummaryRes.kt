package will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.DrinkingMeasurement
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.Drinks
import will.of.d.sulsul.common.findBy
import will.of.d.sulsul.drink.domain.Drink
import java.time.LocalDateTime

class DrinkingMeasurementSummaryRes(
    @Schema(description = "식별 id", example = "6481c97405e8335a58bc4337")
    val id: String,
    @Schema(description = "유저가 총 마신 알코올 양", example = "152.5")
    val totalAlcoholAmount: Double,
    @Schema(description = "유저가 총 마신 술의 잔", example = "4")
    val totalDrinkGlasses: Int,
    @Schema(description = "유저가 마신 날짜", example = "2021-08-20T15:00:00")
    val drankAt: LocalDateTime,
    @Schema(description = "유저가 마신 술의 종류와 잔 수", example = "[{\"drinkType\":\"소주\",\"glasses\":4}]")
    val drinks: List<Drinks>,
    @Schema(description = "칭호 이름", example = "미쳤다")
    val subTitle: String
) {
    companion object {
        fun of(drinkingMeasurement: DrinkingMeasurement): DrinkingMeasurementSummaryRes {
            var totalAlcoholAmount: Double = 0.0

            for (drinks in drinkingMeasurement.drinks) {
                val drink: Drink? = Drink::type findBy drinks.drinkType
                totalAlcoholAmount += drink!!.alcoholAmountPerGlass * drinks.glasses
            }

            return DrinkingMeasurementSummaryRes(
                id = drinkingMeasurement.id.toString(),
                totalAlcoholAmount = totalAlcoholAmount,
                totalDrinkGlasses = drinkingMeasurement.totalDrinkGlasses,
                drankAt = drinkingMeasurement.drankAt,
                drinks = drinkingMeasurement.drinks,
                subTitle = drinkingMeasurement.subTitle
            )
        }
    }
}
