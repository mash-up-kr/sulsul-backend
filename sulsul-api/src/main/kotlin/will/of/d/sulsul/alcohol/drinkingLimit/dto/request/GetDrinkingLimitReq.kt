package will.of.d.sulsul.alcohol.drinkingLimit.dto.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "주량등록 시, 보내야하는 Request Body")
data class GetDrinkingLimitReq(
    @Schema(description = "주종 이름")
    val drinkType: String,

    @Schema(description = "해당 주종을 몇 잔 마실 수 있는지 나타내는 필드")
    val glass: Int
)
