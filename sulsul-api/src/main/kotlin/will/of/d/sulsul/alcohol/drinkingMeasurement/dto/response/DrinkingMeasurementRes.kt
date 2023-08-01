package will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.DrinkingMeasurement
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.Drinks
import will.of.d.sulsul.title.domain.Title
import java.time.LocalDateTime
import kotlin.math.round

class DrinkingMeasurementRes(
    @Schema(description = "식별 id", example = "6481c97405e8335a58bc4337")
    val id: String,
    @Schema(description = "유저가 총 마신 술의 잔", example = "4")
    val totalDrinkGlasses: Int,

    @Schema(description = "칭호", example = "미쳤다")
    val title: String,

    @Schema(description = "카드 이미지 url", example = "https://sulsul-backend.s3.ap-northeast-2.amazonaws.com/static/image/drink/card_soju.png")
    val drinkCardImageUrl: String,

    @Schema(description = "유저가 마신 술의 평균 알콜 도수", example = "16")
    val averageAlcoholPercent: Int,

    @Schema(description = "평균 주량보다 몇 잔 더 마셨는지 나타내는 필드", example = "4")
    val extraGlasses: Int,

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
            val title = Title.defineTitleByAlcoholAmount(drinkingMeasurement.alcoholAmount)

            return DrinkingMeasurementRes(
                id = drinkingMeasurement.id.toString(),
                totalDrinkGlasses = drinkingMeasurement.totalDrinkGlasses,
                title = title.subText,
                drinkCardImageUrl = drinkingMeasurement.drinkCardImageUrl,
                averageAlcoholPercent = round(drinkingMeasurement.averageAlcoholContent).toInt(),
                extraGlasses = drinkingMeasurement.extraGlasses,
                drinkingDuration = drinkingMeasurement.drinkingDuration,
                alcoholCalorie = drinkingMeasurement.alcoholCalorie,
                drankAt = drinkingMeasurement.drankAt,
                drinks = drinkingMeasurement.drinks
            )
        }
    }
}
