package will.of.d.sulsul.alcohol.drinkingMeasurement.dto.request

import jakarta.validation.constraints.NotBlank
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.DrinkingMeasurementDTO
import java.time.LocalDateTime

data class PostDrinkingMeasurementReq(
    @field:NotBlank(message = "측정한 내역을 입력해주세요.")
    val drinks: List<DrinkingMeasurementDTO>,

    @field:NotBlank(message = "술자리가 시작한 시간을 입력해주세요.")
    val drinkingStartTime: LocalDateTime,

    @field:NotBlank(message = "술자리가 끝난 시간을 입력해주세요.")
    val drinkingEndTime: LocalDateTime
)
