package will.of.d.sulsul.title.controller.dto

import will.of.d.sulsul.title.domain.Title

data class TitleDto(
    val title: String,
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
