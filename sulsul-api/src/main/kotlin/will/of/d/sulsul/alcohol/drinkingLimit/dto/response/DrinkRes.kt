package will.of.d.sulsul.alcohol.drinkingLimit.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "주종, 주량을 나타내는 객체")
data class DrinkRes(
    @Schema(description = "주량등록 시 설정한 주종 이름")
    val drinkType: String,

    @Schema(description = "주량등록 시 설정한 잔 수")
    val glass: Int
)
