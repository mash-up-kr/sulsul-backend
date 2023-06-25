package will.of.d.sulsul.alcohol.drinkingLimit.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import will.of.d.sulsul.alcohol.Drink
import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimit
import will.of.d.sulsul.common.findBy

@Schema(description = "주량등록 시 받는 Response 형식")
data class DrinkingLimitRes(
//    val titleOfDrinkingLimit: TitleOfDrinkingLimit,
    @Schema(description = "주량등록 시 설정한 주종 이름")
    val drinkType: String,

    @Schema(description = "주량등록 시 설정한 잔 수")
    val glass: Int,

    @Schema(description = "유저의 주량을 알코올 양으로 표현하는 필드 (단위 g)")
    val totalAlcoholAmount: Double
) {
    companion object {
        fun of(drinkingLimit: DrinkingLimit): DrinkingLimitRes {
//            val titleEnum: TitleOfDrinkingLimit = drinkingLimit.createTitle()
            val drinkEnum: Drink? = Drink::drinkType findBy drinkingLimit.drinkType

            return DrinkingLimitRes(
//                titleOfDrinkingLimit = titleEnum,
                drinkType = drinkEnum!!.drinkType,
                glass = drinkingLimit.glass,
                totalAlcoholAmount = drinkingLimit.alcoholAmount
            )
        }
    }
}
