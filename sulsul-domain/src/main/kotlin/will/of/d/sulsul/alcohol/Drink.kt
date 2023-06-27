package will.of.d.sulsul.alcohol

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class Drink(
    val drinkType: String,
    val alcoholContent: Double,
    val calorie: Int
) {
    SOJU("소주", 16.9, 60),
    WINE("와인", 14.0, 90),
    BEER("맥주", 4.5, 150),
    WHISKY("위스키", 35.0, 90),
    KAOLIANG("고량주", 34.0, 140);
}
