package will.of.d.sulsul.mockapi

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.drink.domain.Drink
import will.of.d.sulsul.title.domain.Title
import will.of.d.sulsul.user.User

@Tag(name = "Mock API 컨트롤러")
@RestController
@RequestMapping("/api/v1")
class MockDrinkingLimitController {

    companion object {
        val mockImageUrl = "https://sulsul-backend.s3.ap-northeast-2.amazonaws.com/static/image/drink/slow_villiage_soju.jpeg"
    }

    @Schema(description = "주량 등록 시, response 되는 데이터")
    data class DrinkingLimitDto(
        @Schema(description = "주종 이름을 나타내는 필드 ('소주', '맥주', '와인', '고량주','위스키'")
        val drinkType: String,
        @Schema(description = "몇 잔 마셨는지를 나타내는 필드")
        val glass: Int,
        @Schema(description = "유저의 주량을 알코올 양으로 표현하는 필드 (단위 mg)")
        val totalAlcoholAmount: Int
    )

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
    data class DrinkingResultDto(
        val drinkType: String,
        val glasses: Int
    )

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
