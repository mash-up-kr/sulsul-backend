package will.of.d.sulsul.alcohol.drinkingLimit.dto.request

import will.of.d.sulsul.alcohol.Drink
import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimit
import will.of.d.sulsul.exception.InvalidRequestException

data class PostDrinkingLimitReq(
    val drinkType: String,
    val drinkBottle: Int
) {

    fun toDocument(kakaoUserId: Long, alcoholAmount: Double): DrinkingLimit {
        if (drinkType != Drink.SOJU.name &&
            drinkType != Drink.WINE.name &&
            drinkType != Drink.BEER.name &&
            drinkType != Drink.WHISKY.name &&
            drinkType != Drink.KAOLIANG.name
        ) {
            throw InvalidRequestException("유효하지 않은 drink type 입니다 (valid : SOJU, WINE, BEER, WHISKY, KAOLIANG)")
        }

        if (drinkBottle < 0) throw InvalidRequestException("유효하지 않은 drink bottle 입니다")

        return DrinkingLimit(
            kakaoUserId = kakaoUserId,
            drinkType = drinkType,
            drinkBottle = drinkBottle,
            alcoholAmount = alcoholAmount
        )
    }
}
