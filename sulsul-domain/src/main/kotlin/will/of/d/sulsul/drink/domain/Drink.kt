package will.of.d.sulsul.drink.domain

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class Drink(
    val type: String,
    val alcoholPercentage: Double,
    val bottleCapacity: Int, // ml
    val alcoholAmountPerGlass: Int, // mg
    val glassCapacity: Int // ml
) {
    SOJU("소주", 16.9, 360, 6700, 50),
    WINE("와인", 14.0, 750, 16600, 150),
    BEER("맥주", 4.5, 500, 12600, 355),
    WHISKY("위스키", 35.0, 700, 8300, 30),
    KAOLIANG("고량주", 34.0, 500, 9000, 25)
    ;
}
