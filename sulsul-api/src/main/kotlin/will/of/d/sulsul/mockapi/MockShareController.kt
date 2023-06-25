package will.of.d.sulsul.mockapi

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.drink.domain.Drink

@Tag(name = "(공유) Mock API 컨트롤러")
@RestController
@RequestMapping("/share/v1")
class MockShareController {
    @Operation(summary = "(공유) 주량 조회 페이지 API", description = "웹에서 링크형식으로 다른 유저들에게 주량을 공유할 때 정보 조회하는 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "(공유) 주량 조회 성공", content = [Content(schema = Schema(implementation = MockDrinkingLimitController.DrinkingLimitDto::class))]),
            ApiResponse(responseCode = "401", description = "토큰 정보 없거나 만료됨", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "500", description = "서버 에러", content = [Content(schema = Schema(implementation = String::class))])
        ]
    )
    @GetMapping("/drinkingLimit")
    fun shareDrinkingLimit(@RequestParam(required = true) token: Long): MockDrinkingLimitController.DrinkingLimitDto {
        return MockDrinkingLimitController.DrinkingLimitDto(
            drinkType = Drink.SOJU.name,
            glass = 8,
            totalAlcoholAmount = Drink.SOJU.alcoholAmountPerGlass * 8
        )
    }
}
