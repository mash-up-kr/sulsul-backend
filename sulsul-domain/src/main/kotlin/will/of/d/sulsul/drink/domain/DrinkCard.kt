package will.of.d.sulsul.drink.domain

const val basUrl = "https://sulsul-backend.s3.ap-northeast-2.amazonaws.com/static/image/drink"

enum class DrinkCard(
    val type: String,
    val drinkCardImageUrl: String
) {
    SOJU("소주", "$basUrl/card_soju.png"),
    WINE("와인", "$basUrl/card_wine.png"),
    BEER("맥주", "$basUrl/card_beer.png"),
    WHISKY("위스키", "$basUrl/card_whisky.png"),
    KAOLIANG("고량주", "$basUrl/card_goryangju.png");
}
