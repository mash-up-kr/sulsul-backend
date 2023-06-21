package will.of.d.sulsul.alcohol.drinkingLimit.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.alcohol.drinkingLimit.dto.request.PostDrinkingLimitReq
import will.of.d.sulsul.alcohol.drinkingLimit.dto.response.DrinkingLimitRes
import will.of.d.sulsul.alcohol.drinkingLimit.service.DrinkingLimitService
import will.of.d.sulsul.user.User

@Tag(name = "주량등록 컨트롤러")
@RestController
@RequestMapping("/api/drinkingLimit")
class DrinkingLimitController(
    private val drinkingLimitService: DrinkingLimitService
) {

    @Operation(summary = "주량 등록 API", description = "로그인 시, 주량을 등록할 때 호출하는 API")
    @PostMapping("")
    fun save(@RequestBody body: PostDrinkingLimitReq, user: User): ResponseEntity<Any> {
        var document = body.toDocument(kakaoUserId = user.kakaoUserId, alcoholAmount = 0.0)
        document = drinkingLimitService.save(document)

        return ResponseEntity.ok(DrinkingLimitRes.of(document))
    }
}
