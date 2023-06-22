package will.of.d.sulsul.alcohol.drinkingLimit

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class TitleOfDrinkingLimit(
    val title: String,
    val tier: String,
    val tierImageUrl: String
) {
    // TODO : 타이틀 이미지 서버에서 내려줄건지 프론트엔드에서 결정할건지 얘기해보고 결정! 지금 생각으론, 웹+안드로이드 있어서 서버에서 내려주는 게 더 좋을 듯 해보임
    BRONZE("술요미", "BRONZE", "https://awss3.com/drinkLimit/title/bronze.jpg"),
    SILVER("술반인", "SILVER", "https://awss3.com/drinkLimit/title/silver.jpg"),
    GOLD("이쯤되면 술잘알", "GOLD", "https://awss3.com/drinkLimit/title/gold.jpg"),
    PLATINUM("알낳괴 (알코올이 낳은 괴물)", "PLATINUM", "https://awss3.com/drinkLimit/title/platinum.jpg"),
    DIAMOND("음주가무 천상계", "DIAMOND", "https://awss3.com/drinkLimit/title/diamond.jpg"),
    MASTER("Alcohol God", "MASTER", "https://awss3.com/drinkLimit/title/diamond.jpg");
}
