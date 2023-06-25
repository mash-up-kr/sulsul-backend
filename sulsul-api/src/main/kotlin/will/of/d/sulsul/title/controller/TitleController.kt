package will.of.d.sulsul.title.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.title.domain.TitleService

@Tag(name = "칭호 컨트롤러")
@RestController
@RequestMapping("/api/v1")
class TitleController(
    private val titleService: TitleService
) {
    @Operation(summary = "칭호 조회 API", description = "칭호를 제공합니다")
    @GetMapping("/title")
    fun getDrinkingLimitTitle(): GetTitleRes {
        return GetTitleRes(
            titleService.getTitles().map {
                TitleDto(
                    titleText = it.text,
                    subTitleText = it.subText,
                    badgeImageUrl = it.badgeImageUrl,
                    cardImageUrl = it.cardImageUrl,
                    alcoholAmount = it.alcoholAmount
                )
            }
        )
    }

    class GetTitleRes(
        val titles: List<TitleDto>
    )

    data class TitleDto(
        val titleText: String,
        val subTitleText: String,
        val badgeImageUrl: String,
        val cardImageUrl: String,
        val alcoholAmount: Double
    )
}
