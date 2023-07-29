package will.of.d.sulsul.alcohol.drinkingMeasurement.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.request.DrinkListReq
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.request.DrinkingMeasurementReq
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response.DrinkingMeasurementByClickRes
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response.DrinkingMeasurementListRes
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response.DrinkingMeasurementRes
import will.of.d.sulsul.alcohol.drinkingMeasurement.service.DrinkingMeasurementApplicationService
import will.of.d.sulsul.title.domain.Title
import will.of.d.sulsul.user.User

@Tag(name = "주량 측정 컨트롤러")
@RestController
@RequestMapping("/api/v1/drinkingReport")
class DrinkingMeasurementController(
    private val drinkingMeasurementApplicationService: DrinkingMeasurementApplicationService
) {
    @Operation(summary = "추량 측정 보고서 생성 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "주량 측정 성공", content = [Content(schema = Schema(implementation = DrinkingMeasurementRes::class))]),
            ApiResponse(responseCode = "400", description = "잘못된 요청 값", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "401", description = "토큰 정보 없거나 만료됨", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "500", description = "서버 에러", content = [Content(schema = Schema(implementation = String::class))])
        ]
    )
    @PostMapping("")
    fun save(@Parameter(hidden = true) user: User, @RequestBody drinkingMeasurementReq: DrinkingMeasurementReq): ResponseEntity<DrinkingMeasurementRes> {
        val res = drinkingMeasurementApplicationService.measurement(user.kakaoUserId, drinkingMeasurementReq)
        return ResponseEntity.ok(res)
    }

    @Operation(summary = "주량 측정 결과 조회 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "주량 측정 결과 조회 성공", content = [Content(schema = Schema(implementation = DrinkingMeasurementRes::class))]),
            ApiResponse(responseCode = "404", description = "레포트가 존재하지 않음", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "401", description = "토큰 정보 없거나 만료됨", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "500", description = "서버 에러", content = [Content(schema = Schema(implementation = String::class))])
        ]
    )
    @GetMapping("/{reportId}")
    fun getReport(@PathVariable reportId: String): ResponseEntity<DrinkingMeasurementRes> {
        val res = drinkingMeasurementApplicationService.getMeasurementReport(reportId)
        return ResponseEntity.ok(res)
    }

    @Operation(summary = "주량 측정 결과 리스트 조회 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "주량 측정 결과 조회 성공", content = [Content(schema = Schema(implementation = DrinkingMeasurementListRes::class))]),
            ApiResponse(responseCode = "404", description = "레포트가 존재하지 않음", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "401", description = "토큰 정보 없거나 만료됨", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "500", description = "서버 에러", content = [Content(schema = Schema(implementation = String::class))])
        ]
    )
    @GetMapping("")
    fun getReportList(@Parameter(hidden = true) user: User): ResponseEntity<DrinkingMeasurementListRes> {
        val res = drinkingMeasurementApplicationService.getMeasurementReportList(user.kakaoUserId)
        return ResponseEntity.ok(res)
    }

    @Operation(summary = "주량측정 시, 클릭할 때마다 호출하는 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "현재 섭취한 주량에 대한 타이틀 조회 성공"),
            ApiResponse(responseCode = "401", description = "토큰 정보 없거나 만료됨", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "500", description = "서버 에러", content = [Content(schema = Schema(implementation = String::class))])
        ]
    )
    @PostMapping("/click-event")
    fun createTemporaryReport(@Parameter(hidden = true) user: User, @RequestBody body: DrinkListReq): ResponseEntity<DrinkingMeasurementByClickRes> {
        val totalAlcoholAmount: Int = drinkingMeasurementApplicationService.calculateAlcoholAmount(body)

        val title: Title = drinkingMeasurementApplicationService.calculateTitle(totalAlcoholAmount)
        val isDrunken: Boolean = drinkingMeasurementApplicationService.calculateDrunkenFlag(totalAlcoholAmount, user.kakaoUserId)

        return ResponseEntity.ok(
            DrinkingMeasurementByClickRes.of(
                title = title,
                isDrunken = isDrunken
            )
        )
    }
}
