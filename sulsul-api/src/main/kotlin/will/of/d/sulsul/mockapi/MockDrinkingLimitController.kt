package will.of.d.sulsul.mockapi

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.user.User

@Tag(name = "주량 등록 컨트롤러")
@RestController
@RequestMapping("/api/v1")
class MockDrinkingLimitController {

    companion object {
        val mockImageUrl = "https://sulsul-backend.s3.ap-northeast-2.amazonaws.com/static/image/drink/slow_villiage_soju.jpeg"
    }

    @Operation(summary = "주종 조회 API", description = "drink amount 단위: ml, alcohol amount 단위: g")
    @GetMapping("/drink")
    fun drink(): List<DrinkDto> {
        return MockDrink.values().map {
            DrinkDto(
                drinkType = it.type,
                alcoholContent = it.alcoholContent,
                bottleCapacity = it.bottleCapacity,
                alcoholAmountPerGlass = it.alcoholAmountPerGlass,
                glassCapacity = it.glassCapacity
            )
        }
    }

    data class DrinkDto(
        val drinkType: String,
        val alcoholContent: Double,
        val bottleCapacity: Int,
        val alcoholAmountPerGlass: Double,
        val glassCapacity: Int
    )

    enum class MockDrink(
        val type: String,
        val alcoholContent: Double,
        val bottleCapacity: Int, // ml
        val alcoholAmountPerGlass: Double, // gram
        val glassCapacity: Int // ml
    ) {
        SOJU("소주", 16.9, 350, 6.7, 50),
        WINE("와인", 14.0, 750, 16.6, 150),
        BEER("맥주", 4.5, 500, 5.3, 150),
        WHISKY("위스키", 35.0, 700, 8.3, 30),
        KAOLIANG("고량주", 34.0, 500, 9.0, 25)
        ;
    }

    @Operation(summary = "칭호 조회 API", description = "칭호를 제공합니다")
    @GetMapping("/drinkingLimit/title")
    fun getDrinkingLimitTitle(): List<TitleDto> {
        return MockTitle.values().map {
            TitleDto(
                titleText = it.text,
                subTitleText = it.subText,
                badgeImageUrl = it.badgeImageUrl,
                cardImageUrl = it.cardImageUrl,
                alcoholAmount = it.alcoholAmount
            )
        }
    }

    enum class MockTitle(
        val text: String,
        val subText: String,
        val badgeImageUrl: String,
        val cardImageUrl: String,
        val alcoholAmount: Double
    ) {
        BRONZE("술요미", "귀엽네", mockImageUrl, mockImageUrl, MockDrink.SOJU.alcoholAmountPerGlass * 7),
        SILVER("술반인", "가자~", mockImageUrl, mockImageUrl, MockDrink.SOJU.alcoholAmountPerGlass * 15),
        GOLD("이쯤되면 술잘알", "술 좀 치네", mockImageUrl, mockImageUrl, MockDrink.SOJU.alcoholAmountPerGlass * 23),
        PLATINUM("알낳괴", "미쳤다", mockImageUrl, mockImageUrl, MockDrink.SOJU.alcoholContent * 31),
        DIAMOND("음주가무 천상계", "알콜 마스터", mockImageUrl, mockImageUrl, MockDrink.SOJU.alcoholContent * 40),
        MASTER("Alcohol God", "알콜 마스터", mockImageUrl, mockImageUrl, MockDrink.SOJU.alcoholContent * 50_000)
    }

    data class TitleDto(
        val titleText: String,
        val subTitleText: String,
        val badgeImageUrl: String,
        val cardImageUrl: String,
        val alcoholAmount: Double
    )

    @Operation(summary = "주량 등록 API", description = "주량을 DB에 저장합니다. 헤더에 토큰이 없으면 저장하지 않습니다.")
    @PostMapping("/drinkingLimit")
    fun postDrinkingLimit(
        @Parameter(hidden = true) user: User?,
        @RequestBody body: List<PostDrinkingLimitReq>
    ): List<DrinkingLimitDto> {
        return listOf(
            DrinkingLimitDto(
                drinkType = MockDrink.SOJU.name,
                drinkingLimit = 5.0,
                userSelect = true
            ),
            DrinkingLimitDto(
                drinkType = MockDrink.WHISKY.name,
                drinkingLimit = 5.0,
                userSelect = false
            ),
            DrinkingLimitDto(
                drinkType = MockDrink.WINE.name,
                drinkingLimit = 5.0,
                userSelect = false
            ),
            DrinkingLimitDto(
                drinkType = MockDrink.BEER.name,
                drinkingLimit = 5.0,
                userSelect = false
            ),
            DrinkingLimitDto(
                drinkType = MockDrink.KAOLIANG.name,
                drinkingLimit = 5.0,
                userSelect = false
            )
        )
    }

    data class PostDrinkingLimitReq(
        val drinkType: String,
        val glass: Int
    )

    data class DrinkingLimitDto(
        val drinkType: String,
        val drinkingLimit: Double,
        val userSelect: Boolean
    )

    @Operation(summary = "주량 조회 페이지 API", description = "DB에 저장된 유저의 주량 데이터를 제공")
    @GetMapping("/drinkingLimit")
    fun getDrinkingLimit(
        @Parameter(hidden = true) user: User
    ): List<DrinkingLimitDto> {
        return listOf(
            DrinkingLimitDto(
                drinkType = MockDrink.SOJU.name,
                drinkingLimit = 5.0,
                userSelect = true
            ),
            DrinkingLimitDto(
                drinkType = MockDrink.WHISKY.name,
                drinkingLimit = 5.0,
                userSelect = false
            ),
            DrinkingLimitDto(
                drinkType = MockDrink.WINE.name,
                drinkingLimit = 5.0,
                userSelect = false
            ),
            DrinkingLimitDto(
                drinkType = MockDrink.BEER.name,
                drinkingLimit = 5.0,
                userSelect = false
            ),
            DrinkingLimitDto(
                drinkType = MockDrink.KAOLIANG.name,
                drinkingLimit = 5.0,
                userSelect = false
            )
        )
    }

    @Operation(summary = "주량 측정 보고서 생성 API", description = "주량 측정 후 측정 결과 보고서를 생성합니다.")
    @PostMapping("/drinkingReport")
    fun postDrinkingReport(@RequestBody body: List<PostDrinkingReportReq>): DrinkingReportDto {
        return DrinkingReportDto(
            totalDrinkGlasses = 25,
            averageAlcoholContent = 16.9,
            drinkingDuration = "3시간 20분",
            alcoholCalorie = 399,
            drinks = listOf(
                DrinkingResultDto(
                    drinkType = MockDrink.SOJU.name,
                    glasses = 1
                ),
                DrinkingResultDto(
                    drinkType = MockDrink.WHISKY.name,
                    glasses = 1
                ),
                DrinkingResultDto(
                    drinkType = MockDrink.WINE.name,
                    glasses = 1
                ),
                DrinkingResultDto(
                    drinkType = MockDrink.BEER.name,
                    glasses = 1
                ),
                DrinkingResultDto(
                    drinkType = MockDrink.KAOLIANG.name,
                    glasses = 1
                )
            )
        )
    }

    data class PostDrinkingReportReq(
        val drinks: List<DrinkingResultDto>,
        val drinkingStartTime: String,
        val drinkingEndTime: String
    )

    data class DrinkingReportDto(
        val totalDrinkGlasses: Int,
        val averageAlcoholContent: Double,
        val drinkingDuration: String,
        val alcoholCalorie: Int,
        val drinks: List<DrinkingResultDto>
    )

    data class DrinkingResultDto(
        val drinkType: String,
        val glasses: Int
    )

    @Operation(summary = "주량 측정 결과 조회 API", description = "유저의 주량 측정 결과를 DB에서 조회합니다.")
    @GetMapping("/drinking/report")
    fun getDrinkingReports(): List<DrinkingReportDto> {
        return listOf(
            DrinkingReportDto(
                totalDrinkGlasses = 25,
                averageAlcoholContent = 16.9,
                drinkingDuration = "3시간 20분",
                alcoholCalorie = 399,
                drinks = listOf(
                    DrinkingResultDto(
                        drinkType = MockDrink.SOJU.name,
                        glasses = 1
                    ),
                    DrinkingResultDto(
                        drinkType = MockDrink.WHISKY.name,
                        glasses = 1
                    ),
                    DrinkingResultDto(
                        drinkType = MockDrink.WINE.name,
                        glasses = 1
                    ),
                    DrinkingResultDto(
                        drinkType = MockDrink.BEER.name,
                        glasses = 1
                    ),
                    DrinkingResultDto(
                        drinkType = MockDrink.KAOLIANG.name,
                        glasses = 1
                    )
                )
            ),
            DrinkingReportDto(
                totalDrinkGlasses = 50,
                averageAlcoholContent = 110.9,
                drinkingDuration = "1시간 20분",
                alcoholCalorie = 222,
                drinks = listOf(
                    DrinkingResultDto(
                        drinkType = MockDrink.SOJU.name,
                        glasses = 4
                    ),
                    DrinkingResultDto(
                        drinkType = MockDrink.WHISKY.name,
                        glasses = 3
                    ),
                    DrinkingResultDto(
                        drinkType = MockDrink.WINE.name,
                        glasses = 2
                    ),
                    DrinkingResultDto(
                        drinkType = MockDrink.BEER.name,
                        glasses = 9
                    ),
                    DrinkingResultDto(
                        drinkType = MockDrink.KAOLIANG.name,
                        glasses = 0
                    )
                )
            )
        )
    }
}
