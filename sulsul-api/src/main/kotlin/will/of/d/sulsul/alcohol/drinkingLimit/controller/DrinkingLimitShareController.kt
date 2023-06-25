package will.of.d.sulsul.alcohol.drinkingLimit.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.alcohol.drinkingLimit.dto.response.DrinkingLimitRes
import will.of.d.sulsul.alcohol.drinkingLimit.service.DrinkingLimitService

@Tag(name = "[공유] 주량등록 컨트롤러")
@RestController
@RequestMapping("/share/drinkingLimit")
class DrinkingLimitShareController(
    private val drinkingLimitService: DrinkingLimitService
) {
    @Operation(summary = "주량 조회 API", description = "주량 조회할 때 호출하는 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "주량 조회 성공", content = [Content(schema = Schema(implementation = DrinkingLimitRes::class))]),
            ApiResponse(responseCode = "400", description = "잘못된 요청 값", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "404", description = "등록된 주량이 없을 경우, 404 반환", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "500", description = "서버 에러", content = [Content(schema = Schema(implementation = String::class))])
        ]
    )
    @GetMapping("")
    fun get(@RequestParam("userId") kakaoUserId: Long): ResponseEntity<Any> {
        val document = drinkingLimitService.findByUserId(kakaoUserId)

        return ResponseEntity.ok(DrinkingLimitRes.of(document))
    }
}
