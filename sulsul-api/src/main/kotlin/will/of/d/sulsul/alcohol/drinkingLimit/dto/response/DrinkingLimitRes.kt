package will.of.d.sulsul.alcohol.drinkingLimit.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimitEntity
import will.of.d.sulsul.drink.domain.Drink
import will.of.d.sulsul.title.domain.Title

@Schema(description = "주량등록 시 받는 Response 형식")
data class DrinkingLimitRes(
    @Schema(description = "내 주량 정보")
    val drink: DrinkRes,

    @Schema(description = "내 칭호 타이틀")
    val title: String,

    @Schema(description = "다른 주종에 대한 주량정보")
    val otherDrinks: List<DrinkRes>,

    @Schema(description = "유저의 주량을 알코올 양으로 표현하는 필드 (단위 mg)")
    val totalAlcoholAmount: Int
) {
    companion object {
        fun of(drinkingLimit: DrinkingLimit): DrinkingLimitRes {
            val ownerDrink = DrinkRes(drinkingLimit.drinkType, drinkingLimit.glass)
            val ownerTitle = Title.defineTitleByAlcoholAmount(drinkingLimit.alcoholAmount)
            val otherDrinks = createOtherDrinks(ownerDrink, drinkingLimit.alcoholAmount)

            return DrinkingLimitRes(
                drink = DrinkRes(drinkingLimit.drinkType, drinkingLimit.glass),
                title = ownerTitle.text,
                otherDrinks = otherDrinks,
                totalAlcoholAmount = drinkingLimitEntity.alcoholAmount
            )
        }

        fun createOtherDrinks(ownerDrink: DrinkRes, totalAlcoholAmount: Int): List<DrinkRes> {
            val otherDrinks = mutableListOf<DrinkRes>()
            Drink.values()

            for (drink in Drink.values()) {
                if (drink.type == ownerDrink.drinkType) continue
                otherDrinks.add(
                    DrinkRes(
                        drinkType = drink.type,
                        glass = (totalAlcoholAmount / drink.alcoholAmountPerGlass)
                    )
                )
            }
            return otherDrinks
        }
    }
}
