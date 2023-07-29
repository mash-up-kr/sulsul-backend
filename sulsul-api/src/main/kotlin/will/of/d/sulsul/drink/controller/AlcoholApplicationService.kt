package will.of.d.sulsul.drink.controller

import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.drinkingLimit.vo.DrinkingLimitVO
import will.of.d.sulsul.drink.domain.Drink

@Service
class AlcoholApplicationService {
    fun calculateDrinkingLimits(alcohol: Int): List<DrinkingLimitVO> {
        return Drink.values().map { drink ->
            DrinkingLimitVO(
                drink = drink,
                glass = alcohol.takeIf { alcohol -> alcohol > 0 }
                    ?.let { alcohol -> (alcohol / drink.alcoholAmountPerGlass).takeIf { it > 0 } ?: 1 }
                    ?: 0
            )
        }
    }

    fun calculateAlcohol(drinkingLimitVO: DrinkingLimitVO): Int {
        return drinkingLimitVO.drink.alcoholAmountPerGlass * drinkingLimitVO.glass
    }
}
