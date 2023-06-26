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
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response.DrinkingMeasurementRes
import will.of.d.sulsul.alcohol.drinkingMeasurement.service.DrinkingMeasurementApplicationService
import will.of.d.sulsul.user.User

@Tag(name = "주량 측정 컨트롤러")
@RestController
@RequestMapping("/api/v1/drinkingMeasurement")
class DrinkingMeasurementController(
    private val drinkingMeasurementApplicationService: DrinkingMeasurementApplicationService
) {
    @Operation(summary = "추량 측정 보고서 생성 API")
    @PostMapping("")
    fun save(user: User, @RequestBody drinkingMeasurementReq: DrinkingMeasurementReq): ResponseEntity<DrinkingMeasurementRes> {
        val res = drinkingMeasurementApplicationService.measurement(user.kakaoUserId, drinkingMeasurementReq)
        return ResponseEntity.ok(res)
    }

    @Operation(summary = "주량 측정 결과 조회 API")
    @GetMapping("/report/{reportId}")
    fun getReport(@PathVariable reportId: String): ResponseEntity<DrinkingMeasurementRes> {
        val res = drinkingMeasurementApplicationService.getMeasurementReport(reportId)
        return ResponseEntity.ok(res)
    }
}
