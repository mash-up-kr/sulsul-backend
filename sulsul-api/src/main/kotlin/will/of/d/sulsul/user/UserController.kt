package will.of.d.sulsul.user

import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.alcohol.drinkingLimit.dto.DrinkingLimitDto
import will.of.d.sulsul.drink.service.AlcoholService
import will.of.d.sulsul.title.dto.TitleDto

@Tag(name = "유저 컨트롤러")
@RestController
class UserController(
    private val drinkService: AlcoholService
) {

    @GetMapping("/api/v1/user/me")
    fun me(@Parameter(hidden = true) user: User): MeRes {
        return MeRes(
            nickname = user.kakaoNickname,
            drinkingLimits = user.drinkingLimit?.let {
                drinkService.calculateDrinkingLimits(it).map { DrinkingLimitDto(drinkType = it.drink.type, glass = it.glass) }
            },
            title = user.title?.let { TitleDto.from(it) }
        )
    }
}

data class MeRes(
    val nickname: String,
    val drinkingLimits: List<DrinkingLimitDto>?,
    val title: TitleDto?
)
