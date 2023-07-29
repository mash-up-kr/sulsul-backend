package will.of.d.sulsul.alcohol.drinkingLimit.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.alcohol.drinkingLimit.dto.DrinkingLimitDto
import will.of.d.sulsul.alcohol.drinkingLimit.dto.request.PostDrinkingLimitReq
import will.of.d.sulsul.alcohol.drinkingLimit.dto.response.PostDrinkingLimitResDto
import will.of.d.sulsul.alcohol.drinkingLimit.service.DrinkingLimitApplicationService
import will.of.d.sulsul.alcohol.drinkingLimit.vo.DrinkingLimitVO
import will.of.d.sulsul.common.findBy
import will.of.d.sulsul.drink.domain.Drink
import will.of.d.sulsul.title.domain.Title
import will.of.d.sulsul.title.dto.TitleDto
import will.of.d.sulsul.user.User

@Tag(name = "주량등록 컨트롤러")
@RestController
@RequestMapping("/api/v1")
class DrinkingLimitController(
    private val drinkingLimitApplicationService: DrinkingLimitApplicationService
) {

    @Operation(summary = "주량 등록 API", description = "로그인 한 뒤, 토큰 정보와 같이 주량을 등록할 때 호출하는 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "주량 등록 성공", content = [Content(schema = Schema(implementation = PostDrinkingLimitResDto::class))]),
            ApiResponse(responseCode = "400", description = "잘못된 요청 값", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "401", description = "토큰 정보 없거나 만료됨", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "500", description = "서버 에러", content = [Content(schema = Schema(implementation = String::class))])
        ]
    )
    @PostMapping("/drinkingLimit")
    fun save(
        @Parameter(hidden = true) user: User?,
        @RequestBody body: PostDrinkingLimitReq
    ): PostDrinkingLimitResDto {
        val drinkingLimits = drinkingLimitApplicationService.saveIfLogin(user, DrinkingLimitVO(drink = (Drink::type findBy body.drinkType)!!, glass = body.glass))
        return PostDrinkingLimitResDto(
            drinks = drinkingLimits.map { DrinkingLimitDto(drinkType = it.drink.type, glass = it.glass) },
            title = TitleDto.from(Title.defineTitleByAlcoholAmount(drinkingLimits.first().getAlcoholAmount()))
        )
    }

    @Operation(summary = "주량 조회 API", description = "주량 조회할 때 호출하는 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "주량 조회 성공", content = [Content(schema = Schema(implementation = PostDrinkingLimitResDto::class))]),
            ApiResponse(responseCode = "400", description = "잘못된 요청 값", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "401", description = "토큰 정보 없거나 만료됨", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "404", description = "등록된 주량이 없을 경우, 404 반환", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "500", description = "서버 에러", content = [Content(schema = Schema(implementation = String::class))])
        ]
    )
    @GetMapping("/drinkingLimit")
    fun get(
        @RequestParam(required = true) drinkType: String,
        @RequestParam(required = true) glass: Int
    ): PostDrinkingLimitResDto {
        val drinkingLimits = drinkingLimitApplicationService.convertToOtherDrinks(DrinkingLimitVO(drink = (Drink::type findBy drinkType)!!, glass = glass))
        return PostDrinkingLimitResDto(
            drinks = drinkingLimits.map { DrinkingLimitDto(drinkType = it.drink.type, glass = it.glass) },
            title = TitleDto.from(Title.defineTitleByAlcoholAmount(drinkingLimits.first().getAlcoholAmount()))
        )
    }
}
