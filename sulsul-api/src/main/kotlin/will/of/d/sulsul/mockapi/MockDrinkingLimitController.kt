package will.of.d.sulsul.mockapi

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.user.User
import java.time.LocalDateTime

@Tag(name = "주량 등록 컨트롤러")
@RestController
@RequestMapping("/api/v1")
class MockDrinkingLimitController {

    companion object {
        val mockImageUrl = "https://sulsul-backend.s3.ap-northeast-2.amazonaws.com/static/image/drink/slow_villiage_soju.jpeg"
    }

    @Operation(summary = "주종 조회 API", description = "drink amount 단위: ml, alcohol amount 단위: g. 하단에 Schemas DrinkDto 참고")
    @GetMapping("/drink")
    fun drink(): GetDrinkRes {
        return GetDrinkRes(
            MockDrink.values().map {
                DrinkDto(
                    drinkType = it.type,
                    alcoholPercentage = it.alcoholContent,
                    bottleCapacity = it.bottleCapacity,
                    alcoholAmountPerGlass = it.alcoholAmountPerGlass,
                    glassCapacity = it.glassCapacity
                )
            }
        )
    }

    data class GetDrinkRes(
        val drinks: List<DrinkDto>
    )

    @Schema(description = "술에 대한 정보")
    data class DrinkDto(
        @Schema(description = "술의 종류. 소주, 와인, 고량주, 위스키, 맥주")
        val drinkType: String,
        @Schema(description = "술 도수. 단위는 %")
        val alcoholPercentage: Double,
        @Schema(description = "술병 용액 양. 단위는 ml")
        val bottleCapacity: Int,
        @Schema(description = "술잔에 포함된 알코올 양. 단위는 g")
        val alcoholAmountPerGlass: Double,
        @Schema(description = "술잔 용약 양. 단위는 ml")
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
    fun postDrinkingReport(@RequestBody body: PostDrinkingReportReq): DrinkingReportDto {
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
                ),
            ),
            drankAt = "2021-08-20T15:00:00"
        )
    }

    data class PostDrinkingReportReq(
        @Schema(description = "총 마신 잔 수", example = "4")
        val totalGlasses: Int,
        @Schema(description = "술 종류와 잔 수", example = "[{\"drinkType\":\"소주\", \"glasses\":4}]")
        val drinks: List<DrinkingResultDto>,
        @Schema(description = "술을 마신 시작 시간", example = "2021-08-20T15:00:00")
        val drinkingStartTime: String,
        @Schema(description = "술을 마신 종료 시간", example = "2021-08-20T18:20:00")
        val drinkingEndTime: String
    )

    data class DrinkingReportDto(
        @Schema(description = "유저가 총 마신 술의 잔", example = "4")
        val totalDrinkGlasses: Int,
        @Schema(description = "유저가 마신 술의 평균 알콜 도수", example = "16.9")
        val averageAlcoholContent: Double,
        @Schema(description = "유저가 술을 마신 시간", example = "3시간 20분")
        val drinkingDuration: String,
        @Schema(description = "유저가 마신 술의 칼로리", example = "399")
        val alcoholCalorie: Int,
        @Schema(description = "유저가 마신 술의 종류와 잔 수", example = "[{\"drinkType\":\"소주\",\"glasses\":4}]")
        val drinks: List<DrinkingResultDto>,
        @Schema(description = "유저가 마신 날짜", example = "2021-08-20T15:00:00")
        val drankAt: String,
    )

    data class DrinkingResultDto(
        val drinkType: String,
        val glasses: Int
    )

    @Operation(summary = "주량 측정 결과 조회 API", description = "유저의 주량 측정 결과를 DB에서 조회합니다.")
    @GetMapping("/drinking/report/{id}")
    fun getDrinkingReports(@PathVariable id: String): DrinkingReportDto {
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
            ),
            drankAt = "2021-08-20T15:00:00"
        )
    }

    @Operation(summary = "술약속 카드 조회 API", description = "유저의 술약속 카드를 DB에서 조회합니다.")
    @GetMapping("/drinking/card")
    fun getDrinkingCards(): GetDrinkingCardsDto {
        return GetDrinkingCardsDto(
            listOf(
                DrinkingCardDto(
                    drinkingReportId = "id1",
                    cardImageUrl = mockImageUrl,
                    drinks = listOf(
                        DrinkingResultDto(
                            drinkType = MockDrink.SOJU.name,
                            glasses = 1
                        ),
                        DrinkingResultDto(
                            drinkType = MockDrink.WHISKY.name,
                            glasses = 1
                        )
                    ),
                    drankDate = "2023-06-24T04:00:00Z",
                    subTitleText = MockTitle.BRONZE.subText
                ),
                DrinkingCardDto(
                    drinkingReportId = "id2",
                    cardImageUrl = mockImageUrl,
                    drinks = listOf(
                        DrinkingResultDto(
                            drinkType = MockDrink.KAOLIANG.name,
                            glasses = 5
                        ),
                        DrinkingResultDto(
                            drinkType = MockDrink.WINE.name,
                            glasses = 1
                        )
                    ),
                    drankDate = "2023-07-24T04:00:00Z",
                    subTitleText = MockTitle.SILVER.subText
                )
            )
        )
    }

    data class GetDrinkingCardsDto(
        val cards: List<DrinkingCardDto>
    )

    @Schema(description = "술약속 카드에 표현될 데이터")
    data class DrinkingCardDto(
        val drinkingReportId: String,
        val cardImageUrl: String,
        val drinks: List<DrinkingResultDto>,
        @Schema(description = "ISO 8601 포맷의 날짜 데이터. e.g. 2023-07-24T04:00:00Z")
        val drankDate: String,
        val subTitleText: String
    )
}
