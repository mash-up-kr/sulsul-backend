package will.of.d.sulsul.alcohol.drinkingLimit.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimit
import will.of.d.sulsul.drink.domain.Drink

@Schema(description = "주량등록 시 받는 Response 형식")
data class DrinkingLimitRes(
//    val titleOfDrinkingLimit: TitleOfDrinkingLimit,
    @Schema(description = "내 주량 정보")
    val myDrink: DrinkRes,

    @Schema(description = "다른 주종에 대한 주량정보")
    val otherDrinks: List<DrinkRes>,

    @Schema(description = "유저의 주량을 알코올 양으로 표현하는 필드 (단위 g)")
    val totalAlcoholAmount: Double
) {
    companion object {
        fun of(drinkingLimit: DrinkingLimit): DrinkingLimitRes {
//            val titleEnum: TitleOfDrinkingLimit = drinkingLimit.createTitle()
//            val drinkEnum: Drink? = Drink::type findBy drinkingLimit.drinkType
            val ownerDrink = DrinkRes(drinkingLimit.drinkType, drinkingLimit.glass)
            val otherDrinks = createOtherDrinks(ownerDrink, drinkingLimit.alcoholAmount)

            return DrinkingLimitRes(
//                titleOfDrinkingLimit = titleEnum,
                myDrink = DrinkRes(drinkingLimit.drinkType, drinkingLimit.glass),
                otherDrinks = otherDrinks,
                totalAlcoholAmount = drinkingLimit.alcoholAmount
            )
        }

        fun createOtherDrinks(ownerDrink: DrinkRes, totalAlcoholAmount: Double): List<DrinkRes> {
            val otherDrinks = mutableListOf<DrinkRes>()
            Drink.values()

            for (drink in Drink.values()) {
                if (drink.type == ownerDrink.drinkType) continue
                otherDrinks.add(
                    DrinkRes(
                        drinkType = drink.type,
                        glass = (totalAlcoholAmount / drink.alcoholAmountPerGlass).toInt()
                    )
                )
            }
            return otherDrinks
        }
    }
}
