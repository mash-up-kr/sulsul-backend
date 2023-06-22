package will.of.d.sulsul.alcohol

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class Drink(
    val drinkType: String,
    val alcoholContent: Double
) {
    SOJU("소주", 16.9),
    WINE("와인", 14.0),
    BEER("맥주", 4.5),
    WHISKY("위스키", 35.0),
    KAOLIANG("고량주", 34.0);
}
