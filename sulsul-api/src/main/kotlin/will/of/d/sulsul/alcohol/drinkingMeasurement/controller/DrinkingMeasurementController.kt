package will.of.d.sulsul.alcohol.drinkingMeasurement.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.request.PostDrinkingMeasurementReq
import will.of.d.sulsul.alcohol.drinkingMeasurement.service.DrinkingMeasurementApplicationService

@Tag(name = "주량 측정 컨트롤러")
@RestController
@RequestMapping("/api/v1/drinkingMeasurement")
class DrinkingMeasurementController(
    private val drinkingMeasurementApplicationService: DrinkingMeasurementApplicationService
) {
    @Operation(summary = "추량 측정 API")
    @PostMapping("")
    fun save(@RequestBody postDrinkingMeasurementReq: PostDrinkingMeasurementReq): ResponseEntity<Any> {
        val res = drinkingMeasurementApplicationService.measurement(1111L, postDrinkingMeasurementReq)
        return ResponseEntity.ok(res)
    }
}
