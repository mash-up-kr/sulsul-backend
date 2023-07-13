package will.of.d.sulsul.alcohol.drinkingLimit.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.alcohol.drinkingLimit.dto.request.PostDrinkingLimitReq
import will.of.d.sulsul.alcohol.drinkingLimit.dto.response.DrinkingLimitRes
import will.of.d.sulsul.alcohol.drinkingLimit.service.DrinkingLimitService
import will.of.d.sulsul.alcohol.drinkingLimit.vo.DrinkingLimitVO
import will.of.d.sulsul.user.User

@Tag(name = "주량등록 컨트롤러")
@RestController
@RequestMapping("/api/v1/drinkingLimit")
class DrinkingLimitController(
    private val drinkingLimitService: DrinkingLimitService
) {

    @Operation(summary = "주량 등록 API", description = "로그인 한 뒤, 토큰 정보와 같이 주량을 등록할 때 호출하는 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "주량 등록 성공", content = [Content(schema = Schema(implementation = DrinkingLimitRes::class))]),
            ApiResponse(responseCode = "400", description = "잘못된 요청 값", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "401", description = "토큰 정보 없거나 만료됨", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "500", description = "서버 에러", content = [Content(schema = Schema(implementation = String::class))])
        ]
    )
    @PostMapping("")
    fun save(
        @Parameter(hidden = true) user: User,
        @RequestBody body: PostDrinkingLimitReq
    ): ResponseEntity<Any> {
        val drinkingLimitVO = DrinkingLimitVO.from(
            kakaoUserId = user.kakaoUserId,
            drinkType = body.drinkType,
            glass = body.glass
        )

        val document = drinkingLimitService.save(drinkingLimitVO)

        return ResponseEntity.ok(DrinkingLimitRes.of(document))
    }

    @Operation(summary = "(공유) 주량 조회 API", description = "로그인 없이, 주량을 등록할 때 호출하는 API. 해당 API 는 DB에 저장하지 않고 클라이언트로부터 받은 값으로 바로 값 계산해서 보여주는 프로세스를 가진다")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "주량 등록 성공", content = [Content(schema = Schema(implementation = DrinkingLimitRes::class))]),
            ApiResponse(responseCode = "400", description = "잘못된 요청 값", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "500", description = "서버 에러", content = [Content(schema = Schema(implementation = String::class))])
        ]
    )
    @GetMapping("/share")
    fun getInShareMode(
        @RequestParam("drinkType") drinkType: String,
        @RequestParam("glass") glass: Int
    ): ResponseEntity<Any> {
        val drinkingLimitVO = DrinkingLimitVO.from(
            kakaoUserId = 0,
            drinkType = drinkType,
            glass = glass
        )

        val temporaryDocument = drinkingLimitService.getInShare(drinkingLimitVO)

        return ResponseEntity.ok(DrinkingLimitRes.of(temporaryDocument))
    }

    @Operation(summary = "주량 조회 API", description = "주량 조회할 때 호출하는 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "주량 조회 성공", content = [Content(schema = Schema(implementation = DrinkingLimitRes::class))]),
            ApiResponse(responseCode = "400", description = "잘못된 요청 값", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "401", description = "토큰 정보 없거나 만료됨", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "404", description = "등록된 주량이 없을 경우, 404 반환", content = [Content(schema = Schema(implementation = String::class))]),
            ApiResponse(responseCode = "500", description = "서버 에러", content = [Content(schema = Schema(implementation = String::class))])
        ]
    )
    @GetMapping("")
    fun get(@Parameter(hidden = true) user: User): ResponseEntity<Any> {
        val document = drinkingLimitService.findByUserId(user.kakaoUserId)

        return ResponseEntity.ok(DrinkingLimitRes.of(document))
    }
}
