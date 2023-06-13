package will.of.d.sulsul.alcohol.drinkingLimit.dto.request

import org.bson.types.ObjectId
import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimit

data class PostDrinkingLimitReq(
    val userId: String,
    val sojuCount: Int,
    val beerCount: Int,
    val alcoholAmount: Int = 0

    // TODO : 주종 추가되면 주종 추가하기
) {

    fun toDocument(alcoholAmount: Double): DrinkingLimit {
        return DrinkingLimit(
            userId = ObjectId(userId),
            sojuCount = sojuCount,
            beerCount = beerCount,
            alcoholAmount = alcoholAmount
        )
    }
}
