package will.of.d.sulsul.alcohol.drinkingMeasurement.dto.request

import io.swagger.v3.oas.annotations.media.Schema

data class DrinkListReq(
    val drinks: List<DrinkReq>
)

data class DrinkReq(
    @Schema(description = "술의 종류", allowableValues = ["소주", "와인", "맥주", "위스키", "고량주"])
    val drinkType: String,
    val glass: Int
)
