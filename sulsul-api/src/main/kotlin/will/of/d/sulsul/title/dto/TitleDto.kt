package will.of.d.sulsul.title.dto

import io.swagger.v3.oas.annotations.media.Schema
import will.of.d.sulsul.title.domain.Title

data class TitleDto(
    @Schema(allowableValues = ["술요미", "술반인", "이쯤되면 술잘알", "알낳괴", "음주가무 천상계", "Alcohol God"])
    val title: String,
    @Schema(allowableValues = ["귀엽네", "가자~", "술 좀 치네", "미쳤다", "알콜 마스터", "무서울 게 없다"])
    val subTitle: String,
    val badgeImageUrl: String,
    val cardImageUrl: String
) {
    companion object {
        fun from(title: Title) = TitleDto(
            title = title.text,
            subTitle = title.subText,
            badgeImageUrl = title.badgeImageUrl,
            cardImageUrl = title.cardImageUrl
        )
    }
}
