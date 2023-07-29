package will.of.d.sulsul.drink.controller

import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.drinkingLimit.vo.DrinkingLimitVO
import will.of.d.sulsul.drink.domain.Drink

@Service
class DrinkingService {

    fun calculateGlassBy(alcohol: Int): List<DrinkingLimitVO> {
        return Drink.values().map { it ->
            DrinkingLimitVO(
                kakaoUserId = 0, // FIXME: DrinkingLimitV0에서 kakaoUserId 없애야함.
                drinkType = it.type,
                glass = (alcohol / it.alcoholAmountPerGlass).let { glass ->
                    if (glass == 0) {
                        glass + 1
                    }

                    glass.toInt()
                }
            )
        }
    }
}
