package will.of.d.sulsul.mockapi

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.drink.domain.Drink
import will.of.d.sulsul.title.domain.Title
import will.of.d.sulsul.user.User
import java.time.LocalDateTime

@Tag(name = "Mock API 컨트롤러")
@RestController
@RequestMapping("/api/v1")
class MockDrinkingLimitController {

    companion object {
        val mockImageUrl = "https://sulsul-backend.s3.ap-northeast-2.amazonaws.com/static/image/drink/slow_villiage_soju.jpeg"
    }

    @Operation(summary = "주량 등록 API", description = "주량을 DB에 저장합니다. 헤더에 토큰이 없으면 저장하지 않습니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "주량 등록 성공", content = [Content(schema = Schema(implementation = DrinkingLimitDto::class))]),
            ApiResponse(responseCode = "401", description = "토큰 정보 없거나 만료됨", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "500", description = "서버 에러", content = [Content(schema = Schema(implementation = String::class))])
        ]
    )
    @PostMapping("/drinkingLimit")
    fun postDrinkingLimit(
        @Parameter(hidden = true) user: User?,
        @RequestBody body: PostDrinkingLimitReq
    ): DrinkingLimitDto {
        return DrinkingLimitDto(
            drinkType = Drink.SOJU.type,
            glass = 8,
            totalAlcoholAmount = Drink.SOJU.alcoholAmountPerGlass * 8
        )
    }

    data class PostDrinkingLimitReq(
        val drinkType: String,
        val glass: Int
    )

    @Schema(description = "주량 등록 시, response 되는 데이터")
    data class DrinkingLimitDto(
        @Schema(description = "주종 이름을 나타내는 필드 ('소주', '맥주', '와인', '고량주','위스키'")
        val drinkType: String,
        @Schema(description = "몇 잔 마셨는지를 나타내는 필드")
        val glass: Int,
        @Schema(description = "유저의 주량을 알코올 양으로 표현하는 필드 (단위 g)")
        val totalAlcoholAmount: Double
    )

    @Operation(summary = "주량 조회 페이지 API", description = "DB에 저장된 유저의 주량 데이터를 제공")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "주량 조회 성공", content = [Content(schema = Schema(implementation = DrinkingLimitDto::class))]),
            ApiResponse(responseCode = "401", description = "토큰 정보 없거나 만료됨", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "500", description = "서버 에러", content = [Content(schema = Schema(implementation = String::class))])
        ]
    )
    @GetMapping("/drinkingLimit")
    fun getDrinkingLimit(
        @Parameter(hidden = true) user: User
    ): DrinkingLimitDto {
        return DrinkingLimitDto(
            drinkType = Drink.SOJU.type,
            glass = 8,
            totalAlcoholAmount = Drink.SOJU.alcoholAmountPerGlass * 8
        )
    }

    data class DrinkingLimitListDto(
        val drinkList: List<DrinkingLimitDto>
    )

    @Operation(summary = "다른 주종별 주량 조회 API", description = "유저 주량으로 다양한 주종별 맥시멈 주량 조회 API (* response의 glass는 주종별 maxium glass 의미함")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "다른 주종별 주량 조회 성공", content = [Content(schema = Schema(implementation = DrinkingLimitListDto::class))]),
            ApiResponse(responseCode = "401", description = "토큰 정보 없거나 만료됨", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "500", description = "서버 에러", content = [Content(schema = Schema(implementation = String::class))])
        ]
    )
    @GetMapping("/drinkingLimit/differentDrink")
    fun getDifferentDrinkingLimit(
        @Parameter(hidden = true) user: User
    ): DrinkingLimitListDto {
        val totalAlcoholAmount = Drink.SOJU.alcoholAmountPerGlass * 8

        return DrinkingLimitListDto(
            drinkList = listOf(
                DrinkingLimitDto(
                    drinkType = Drink.SOJU.name,
                    glass = 8,
                    totalAlcoholAmount = totalAlcoholAmount
                ),
                DrinkingLimitDto(
                    drinkType = Drink.BEER.name,
                    glass = 24,
                    totalAlcoholAmount = totalAlcoholAmount
                ),
                DrinkingLimitDto(
                    drinkType = Drink.WINE.name,
                    glass = 9,
                    totalAlcoholAmount = totalAlcoholAmount
                ),
                DrinkingLimitDto(
                    drinkType = Drink.WHISKY.name,
                    glass = 4,
                    totalAlcoholAmount = totalAlcoholAmount
                ),
                DrinkingLimitDto(
                    drinkType = Drink.KAOLIANG.name,
                    glass = 4,
                    totalAlcoholAmount = totalAlcoholAmount
                )
            )
        )
    }

    @Operation(summary = "주량 측정 보고서 생성 API", description = "주량 측정 후 측정 결과 보고서를 생성합니다.")
    @PostMapping("/drinkingReport")
    fun postDrinkingReport(@RequestBody body: PostDrinkingReportReq): DrinkingReportDto {
        return DrinkingReportDto(
            id = "6481c97405e8335a58bc4337",
            totalDrinkGlasses = 25,
            averageAlcoholContent = 16.9,
            drinkingDuration = "3시간 20분",
            alcoholCalorie = 399,
            drinks = listOf(
                DrinkingResultDto(
                    drinkType = Drink.SOJU.name,
                    glasses = 1
                ),
                DrinkingResultDto(
                    drinkType = Drink.WHISKY.name,
                    glasses = 1
                ),
                DrinkingResultDto(
                    drinkType = Drink.WINE.name,
                    glasses = 1
                ),
                DrinkingResultDto(
                    drinkType = Drink.BEER.name,
                    glasses = 1
                ),
                DrinkingResultDto(
                    drinkType = Drink.KAOLIANG.name,
                    glasses = 1
                )
            ),
            drankAt = LocalDateTime.parse("2021-08-20T15:00:00")
        )
    }

    data class PostDrinkingReportReq(
        @Schema(description = "총 마신 잔 수", example = "4")
        val totalDrinkGlasses: Int,
        @Schema(description = "술 종류와 잔 수", example = "[{\"drinkType\":\"소주\", \"glasses\":4}]")
        val drinks: List<DrinkingResultDto>,
        @Schema(description = "술을 마신 시작 시간", example = "2021-08-20T15:00:00")
        val drinkingStartTime: LocalDateTime,
        @Schema(description = "술을 마신 종료 시간", example = "2021-08-20T18:20:00")
        val drinkingEndTime: LocalDateTime
    )

    data class DrinkingReportDto(
        @Schema(description = "식별 id", example = "6481c97405e8335a58bc4337")
        val id: String,
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
        val drankAt: LocalDateTime
    )

    data class DrinkingResultDto(
        val drinkType: String,
        val glasses: Int
    )

    @Operation(summary = "주량 측정 결과 조회 API", description = "유저의 주량 측정 결과를 DB에서 조회합니다.")
    @GetMapping("/drinking/report/{id}")
    fun getDrinkingReports(@PathVariable id: String): DrinkingReportDto {
        return DrinkingReportDto(
            id = "6481c97405e8335a58bc4337",
            totalDrinkGlasses = 25,
            averageAlcoholContent = 16.9,
            drinkingDuration = "3시간 20분",
            alcoholCalorie = 399,
            drinks = listOf(
                DrinkingResultDto(
                    drinkType = Drink.SOJU.name,
                    glasses = 1
                ),
                DrinkingResultDto(
                    drinkType = Drink.WHISKY.name,
                    glasses = 1
                ),
                DrinkingResultDto(
                    drinkType = Drink.WINE.name,
                    glasses = 1
                ),
                DrinkingResultDto(
                    drinkType = Drink.BEER.name,
                    glasses = 1
                ),
                DrinkingResultDto(
                    drinkType = Drink.KAOLIANG.name,
                    glasses = 1
                )
            ),
            drankAt = LocalDateTime.parse("2021-08-20T15:00:00")
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
                            drinkType = Drink.SOJU.name,
                            glasses = 1
                        ),
                        DrinkingResultDto(
                            drinkType = Drink.WHISKY.name,
                            glasses = 1
                        )
                    ),
                    drankDate = "2023-06-24T04:00:00Z",
                    subTitleText = Title.BRONZE.subText
                ),
                DrinkingCardDto(
                    drinkingReportId = "id2",
                    cardImageUrl = mockImageUrl,
                    drinks = listOf(
                        DrinkingResultDto(
                            drinkType = Drink.KAOLIANG.name,
                            glasses = 5
                        ),
                        DrinkingResultDto(
                            drinkType = Drink.WINE.name,
                            glasses = 1
                        )
                    ),
                    drankDate = "2023-07-24T04:00:00Z",
                    subTitleText = Title.SILVER.subText
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
