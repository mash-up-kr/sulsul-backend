package will.of.d.sulsul.alcohol.drinkingMeasurement.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import will.of.d.sulsul.alcohol.drinkingMeasurement.vo.DrinkingAmountVO
import java.time.LocalDateTime

data class DrinkingMeasurementReq(
    @Schema(description = "술 종류와 잔 수", example = "[{\"drinkType\":\"소주\", \"glasses\":4}]")
    @field:NotBlank(message = "측정한 내역을 입력해주세요.")
    val drinks: List<DrinkingAmountVO>,

    @Schema(description = "술을 마신 시작 시간", example = "2021-08-20T15:00:00")
    @field:NotBlank(message = "술자리가 시작한 시간을 입력해주세요.")
    val drinkingStartTime: LocalDateTime,

    @Schema(description = "술을 마신 종료 시간", example = "2021-08-20T18:20:00")
    @field:NotBlank(message = "술자리가 끝난 시간을 입력해주세요.")
    val drinkingEndTime: LocalDateTime,

    @Schema(description = "총 마신 잔 수", example = "4")
    @field:NotBlank(message = "유저가 마신 총 잔 수를 입력해주세요.")
    val totalDrinkGlasses: Int
)
