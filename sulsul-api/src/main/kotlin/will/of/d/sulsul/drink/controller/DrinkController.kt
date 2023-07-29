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
        return GetDrinkRes(
            drinkService.getDrinks().map {
                DrinkDto(
                    drinkType = it.type,
                    alcoholPercentage = it.alcoholPercentage,
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
        @Schema(description = "술의 종류", allowableValues = ["소주", "와인", "맥주", "위스키", "고량주"])
        val drinkType: String,
        @Schema(description = "술 도수. 단위는 %")
        val alcoholPercentage: Double,
        @Schema(description = "술병 용액 양. 단위는 ml")
        val bottleCapacity: Int,
        @Schema(description = "술잔에 포함된 알코올 양. 단위는 mg")
        val alcoholAmountPerGlass: Int,
        @Schema(description = "술잔 용약 양. 단위는 ml")
        val glassCapacity: Int
    )
}
