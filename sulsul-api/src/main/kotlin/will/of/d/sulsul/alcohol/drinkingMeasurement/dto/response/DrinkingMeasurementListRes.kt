package will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response

import io.swagger.v3.oas.annotations.media.Schema

class DrinkingMeasurementListRes(
    @Schema(description = "주량 측정 결과 리스트")
    val cards: List<DrinkingMeasurementRes>
)
