package will.of.d.sulsul.alcohol.drinkingLimit

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class TitleOfDrinkingLimit(
    val title: String,
    val imageUrl: String
) {
    BRONZE("술요미", "https://sulsul-backend.s3.ap-northeast-2.amazonaws.com/static/image/title/bronze.png"),
    SILVER("술반인", "https://sulsul-backend.s3.ap-northeast-2.amazonaws.com/static/image/title/silver.png"),
    GOLD("이쯤되면 술잘알", "https://sulsul-backend.s3.ap-northeast-2.amazonaws.com/static/image/title/gold.png"),
    PLATINUM("알낳괴 (알코올이 낳은 괴물)", "https://sulsul-backend.s3.ap-northeast-2.amazonaws.com/static/image/title/platinum.png"),
    DIAMOND("음주가무 천상계", "https://sulsul-backend.s3.ap-northeast-2.amazonaws.com/static/image/title/diamond.png"),
    MASTER("Alcohol God", "https://sulsul-backend.s3.ap-northeast-2.amazonaws.com/static/image/title/master.png");
}
