package will.of.d.sulsul.title.domain

import will.of.d.sulsul.drink.domain.Drink

const val mockImageUrl = "https://sulsul-backend.s3.ap-northeast-2.amazonaws.com/static/image/drink/slow_villiage_soju.jpeg"

enum class Title(
    val text: String,
    val subText: String,
    val badgeImageUrl: String,
    val cardImageUrl: String,
    val minAlcoholAmount: Int,
    val maxAlcoholAmount: Int
) {
    BRONZE("술요미", "귀엽네", mockImageUrl, mockImageUrl, 0, Drink.SOJU.alcoholAmountPerGlass * 7),
    SILVER("술반인", "가자~", mockImageUrl, mockImageUrl, BRONZE.maxAlcoholAmount + 1, Drink.SOJU.alcoholAmountPerGlass * 15),
    GOLD("이쯤되면 술잘알", "술 좀 치네", mockImageUrl, mockImageUrl, SILVER.maxAlcoholAmount + 1, Drink.SOJU.alcoholAmountPerGlass * 23),
    PLATINUM("알낳괴", "미쳤다", mockImageUrl, mockImageUrl, GOLD.maxAlcoholAmount + 1, Drink.SOJU.alcoholAmountPerGlass * 31),
    DIAMOND("음주가무 천상계", "알콜 마스터", mockImageUrl, mockImageUrl, PLATINUM.maxAlcoholAmount + 1, Drink.SOJU.alcoholAmountPerGlass * 40),
    MASTER("Alcohol God", "무서울 게 없다", mockImageUrl, mockImageUrl, DIAMOND.maxAlcoholAmount + 1, Drink.SOJU.alcoholAmountPerGlass * 50_000);

    companion object {
        private val textMap = Title.values().associateBy { it.text }
        fun from(text: String) = textMap[text]
        fun defineTitleByAlcoholAmount(alcoholAmount: Int): Title {
            return when (alcoholAmount) {
                in BRONZE.minAlcoholAmount..BRONZE.maxAlcoholAmount -> BRONZE
                in SILVER.minAlcoholAmount..SILVER.maxAlcoholAmount -> SILVER
                in GOLD.minAlcoholAmount..GOLD.maxAlcoholAmount -> GOLD
                in PLATINUM.minAlcoholAmount..PLATINUM.maxAlcoholAmount -> PLATINUM
                in DIAMOND.minAlcoholAmount..DIAMOND.maxAlcoholAmount -> DIAMOND
                else -> MASTER
            }
        }
    }
}
