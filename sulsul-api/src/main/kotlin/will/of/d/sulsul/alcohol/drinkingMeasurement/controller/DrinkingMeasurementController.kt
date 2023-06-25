package will.of.d.sulsul.alcohol.drinkingMeasurement.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.request.DrinkingMeasurementReq
import will.of.d.sulsul.alcohol.drinkingMeasurement.service.DrinkingMeasurementApplicationService

@Tag(name = "주량 측정 컨트롤러")
@RestController
@RequestMapping("/api/v1/drinkingMeasurement")
class DrinkingMeasurementController(
    private val drinkingMeasurementApplicationService: DrinkingMeasurementApplicationService
) {
    @Operation(summary = "추량 측정 보고서 생성 API")
    @PostMapping("")
    fun save(@RequestBody drinkingMeasurementReq: DrinkingMeasurementReq): ResponseEntity<Any> {
        val res = drinkingMeasurementApplicationService.measurement(1111L, drinkingMeasurementReq)
        return ResponseEntity.ok(res)
    }

    @Operation(summary = "주량 측정 결과 조회 API")
    @GetMapping("/report/{reportId}")
    fun getReport(@PathVariable reportId: String): ResponseEntity<Any> {
        val res = drinkingMeasurementApplicationService.getMeasurementReport(reportId)
        return ResponseEntity.ok(res)
    }
}
