package will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response

import will.of.d.sulsul.title.domain.Title

data class DrinkingMeasurementByClickRes(
    val title: DrinkingMeasurementTitleRes,
    val isDrunken: Boolean
) {
    companion object {
        fun of(title: Title, isDrunken: Boolean): DrinkingMeasurementByClickRes {
            return DrinkingMeasurementByClickRes(
                title = DrinkingMeasurementTitleRes(
                    text = title.subText,
                    imageUrl = title.cardImageUrl
                ),
                isDrunken = isDrunken
            )
        }
    }
}

data class DrinkingMeasurementTitleRes(
    val text: String,
    val imageUrl: String
)
