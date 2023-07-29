package will.of.d.sulsul.alcohol.drinkingMeasurement.dto.request

data class DrinkListReq(
    val drinks: List<DrinkReq>
)

data class DrinkReq(
    val drinkType: String,
    val glass: Int
)
