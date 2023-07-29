package will.of.d.sulsul.drink.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.drink.service.DrinkService

@Tag(name = "주종 컨트롤러")
@RestController
@RequestMapping("/api/v1")
class DrinkController(
    private val drinkService: DrinkService
) {

    @Operation(summary = "주종 조회 API", description = "drink amount 단위: ml, alcohol amount 단위: g. 하단에 Schemas DrinkDto 참고")
    @GetMapping("/drink")
    fun drink(): GetDrinkRes {
        return GetDrinkRes(drinkService.getDrinks().map { it.type })
    }

    data class GetDrinkRes(
        @Schema(description = "술의 종류", example = "[\"소주\", \"와인\", \"맥주\", \"위스키\", \"고량주\"]")
        val drinks: List<String>
    )
}
