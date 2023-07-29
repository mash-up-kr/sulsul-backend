package will.of.d.sulsul.alcohol.drinkingLimit.dto.response

import will.of.d.sulsul.alcohol.drinkingLimit.dto.DrinkingLimitDto
import will.of.d.sulsul.title.controller.dto.TitleDto

data class PostDrinkingLimitResDto(
    val drinks: List<DrinkingLimitDto>,
    val title: TitleDto
)
