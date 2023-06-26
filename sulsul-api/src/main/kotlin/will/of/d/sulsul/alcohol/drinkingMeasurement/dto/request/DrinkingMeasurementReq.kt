package will.of.d.sulsul.alcohol.drinkingMeasurement.dto.request

import jakarta.validation.constraints.NotBlank
import will.of.d.sulsul.alcohol.drinkingMeasurement.vo.DrinkingAmountVO
import java.time.LocalDateTime

data class DrinkingMeasurementReq(
    @field:NotBlank(message = "측정한 내역을 입력해주세요.")
    val drinks: List<DrinkingAmountVO>,

    @field:NotBlank(message = "술자리가 시작한 시간을 입력해주세요.")
    val drinkingStartTime: LocalDateTime,

    @field:NotBlank(message = "술자리가 끝난 시간을 입력해주세요.")
    val drinkingEndTime: LocalDateTime,

    @field:NotBlank(message = "유저가 마신 총 잔 수를 입력해주세요.")
    val totalDrinkGlasses: Int
)
