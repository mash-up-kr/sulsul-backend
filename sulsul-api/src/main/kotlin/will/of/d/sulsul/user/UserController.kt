package will.of.d.sulsul.user

import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.drink.controller.DrinkingService

@RestController
class UserController(
    private val drinkService: DrinkingService
) {

    @GetMapping("/api/v1/user/me")
    fun me(@Parameter(hidden = true) user: User): MeRes {
        return MeRes(
            nickname = user.kakaoNickname,
            drinkingLimits = user.drinkingLimit?.let {
                drinkService.calculateGlassBy(it).map { DrinkLimit(type = it.drinkType, glass = it.glass) }
            },
            title = user.title?.let { TitleRes(it.text, it.subText, it.cardImageUrl, it.badgeImageUrl) }
        )
    }
}

data class MeRes(
    val nickname: String,
    val drinkingLimits: List<DrinkLimit>?,
    val title: TitleRes?
)

data class DrinkLimit(
    val type: String,
    val glass: Int
)

data class TitleRes(
    val title: String,
    val subTitle: String,
    val cardImageUrl: String,
    val badgeImageUrl: String
)
