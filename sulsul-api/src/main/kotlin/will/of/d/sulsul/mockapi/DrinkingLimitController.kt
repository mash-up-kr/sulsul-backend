package will.of.d.sulsul.mockapi

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.user.User
import java.net.URI

@Tag(name = "주량 등록 컨트롤러")
@RestController
@RequestMapping("/api/v1")
class DrinkingLimitController {

    @Operation(summary = "주량 등록 페이지에서 호출해야 하는 API", description = "주종은 서버에서 관리할게요~")
    @GetMapping("/drinkingLimit")
    fun getDrinkingLimit(): GetDrinkingLimitRes {
        return GetDrinkingLimitRes(drinkTypes = Drink.values().map { it.type })
    }

    data class GetDrinkingLimitRes(
        val drinkTypes: List<String>
    )

    @Operation(summary = "주량을 등록하는 API (Android)")
    @PostMapping("/android/drinkingLimit")
    fun postDrinkingLimitAmountForAndroid(
        @Parameter(hidden = true) user: User,
        @RequestBody body: PostDrinkingLimitReq
    ): ResponseEntity<Unit> {
        return ResponseEntity.created(URI.create("Drinking Limit"))
            .build()
    }

    data class PostDrinkingLimitReq(
        val drinkType: String,
        val drinkLimit: Int
    )

    @Operation(summary = "주량을 등록하는 API (Web)")
    @PostMapping("/web/drinkingLimit")
    fun postDrinkingLimitAmountForWeb(
        @RequestBody body: PostDrinkingLimitReq
    ): PostDrinkIngLimitResForWeb {
        return PostDrinkIngLimitResForWeb(
            title = "술요미",
            titleCardUrl = "https://sulsul-backend.s3.ap-northeast-2.amazonaws.com/static/image/drink/slow_villiage_soju.jpeg",
            drinkingLimit = listOf(
                DrinkingLimitsRes(
                    drinkType = Drink.SOJU.name,
                    alcoholContent = Drink.SOJU.alcoholContent,
                    drinkingLimit = "1잔",
                    userSelect = true
                ),
                DrinkingLimitsRes(
                    drinkType = Drink.WHISKY.name,
                    alcoholContent = Drink.WHISKY.alcoholContent,
                    drinkingLimit = "1잔",
                    userSelect = false
                ),
                DrinkingLimitsRes(
                    drinkType = Drink.WINE.name,
                    alcoholContent = Drink.WINE.alcoholContent,
                    drinkingLimit = "1잔",
                    userSelect = false
                ),
                DrinkingLimitsRes(
                    drinkType = Drink.BEER.name,
                    alcoholContent = Drink.BEER.alcoholContent,
                    drinkingLimit = "1잔",
                    userSelect = false
                ),
                DrinkingLimitsRes(
                    drinkType = Drink.KAOLIANG.name,
                    alcoholContent = Drink.KAOLIANG.alcoholContent,
                    drinkingLimit = "1잔",
                    userSelect = false
                )
            )
        )
    }

    data class PostDrinkIngLimitResForWeb(
        val title: String,
        val titleCardUrl: String,
        val drinkingLimit: List<DrinkingLimitsRes>
    )

    data class DrinkingLimitsRes(
        val drinkType: String,
        val alcoholContent: Double,
        val drinkingLimit: String,
        val userSelect: Boolean
    )

    @Operation(summary = "주량 단위 변환 API (인증불필요)")
    @GetMapping("/drinkingAmount")
    fun getDrinkingAmount(
        @RequestParam(required = true) drinkType: String,
        @RequestParam(required = true) glasses: Int
    ): GetDrinkingAmountRes {
        return GetDrinkingAmountRes(
            glasses = glasses + 1,
            ml = 350 * glasses,
            bottles = 0.1f + glasses
        )
    }

    data class GetDrinkingAmountRes(
        val glasses: Int,
        val ml: Int,
        val bottles: Float
    )
}

enum class Drink(
    val type: String,
    val alcoholContent: Double
) {
    SOJU("소주", 16.9),
    WINE("와인", 14.0),
    BEER("맥주", 4.5),
    WHISKY("위스키", 35.0),
    KAOLIANG("고량주", 34.0)
    ;
}
